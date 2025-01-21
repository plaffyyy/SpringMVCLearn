package ru.zimin.springcourse.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.zimin.springcourse.dao.BookDAO;
import ru.zimin.springcourse.dao.PersonDAO;
import ru.zimin.springcourse.models.Book;

import java.sql.SQLException;


@Controller
@RequestMapping("/books")
public class BookController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String booksPage(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "books/indexBooks";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) throws SQLException {

        Book book = bookDAO.show(id);

        model.addAttribute("book", book);
        model.addAttribute("people", personDAO.index());
        if (book.getPerson_id() != null) {
            model.addAttribute("reader", personDAO.show(book.getPerson_id()));
        }

        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(Model model) {
        model.addAttribute("book", new Book());
        return "books/newBook";
    }

    @PostMapping
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "books/newBook";
        }
        bookDAO.save(book);
        return "redirect:/books";

    }

    @GetMapping("/{book_id}/edit")
    public String edit(Model model, @PathVariable("book_id") int id) {
        model.addAttribute("book", bookDAO.show(id));
        return "books/editBook";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute @Valid Book book,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {

        if (bindingResult.hasErrors()) {
            return "books/editBook";
        }

        bookDAO.update(id, book);

        return "redirect:/books";

    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {

        bookDAO.remove(id);

        return "redirect:/books";
    }

    @PostMapping("/{book_id}/person")
    public String designateBook(@PathVariable("book_id") int book_id,
                                @RequestParam("person_id") int person_id) {

        bookDAO.updatePerson(book_id, person_id);

        return "redirect:/books";

    }

    @PatchMapping("/{book_id}/reader")
    public String freeBook(@PathVariable("book_id") int book_id) {

        bookDAO.updatePerson(book_id, null);

        return "redirect:/books/" + book_id;
    }



}
