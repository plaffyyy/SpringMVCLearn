package ru.zimin.springcourse.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.zimin.springcourse.dao.BookDAO;
import ru.zimin.springcourse.dao.PersonDAO;
import ru.zimin.springcourse.models.Person;
import ru.zimin.springcourse.util.PersonValidator;

import java.sql.SQLException;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;
    private final BookDAO bookDAO;
    private final PersonValidator personValidator;
    @Autowired
    public PeopleController(PersonDAO personDAO, BookDAO bookDAO, PersonValidator personValidator) {
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
        this.personValidator = personValidator;
    }

    //получаем всех людей из DAO и передаем на отображение в представление
    @GetMapping()
    public String index(
            Model model
    ) {
        try {
            model.addAttribute("people", personDAO.index());
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return "people/index";
    }

    //получаем одного человека по id из DAO и передаем на отображение в представление
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {

        model.addAttribute("person", personDAO.show(id));
        model.addAttribute("books", bookDAO.getAll(id));

        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("person", new Person());
        return "people/newPerson";
    }

    @PostMapping
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {

        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "people/newPerson";
        }
        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDAO.show(id));
        return "people/editPerson";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute @Valid Person person,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
//        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "people/editPerson";
        }

        personDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }
}
