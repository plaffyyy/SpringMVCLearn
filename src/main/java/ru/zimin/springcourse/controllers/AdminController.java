package ru.zimin.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.zimin.springcourse.dao.PersonDAO;
import ru.zimin.springcourse.models.Person;

import java.sql.SQLException;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final PersonDAO personDAO;

    @Autowired
    public AdminController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String adminPage(Model model, @ModelAttribute("person")Person person) throws SQLException {

        model.addAttribute("people", personDAO.index());
        return "/admins/adminPage";
    }
    @PostMapping("/add")
    private String appointAdmin(@ModelAttribute("person")Person person) {

        Person foundPerson = personDAO.show(person.getId());
        foundPerson.setAdmin(true);
        personDAO.updateStatus(foundPerson.getId(), foundPerson);

        return "redirect:/people";

    }



}
