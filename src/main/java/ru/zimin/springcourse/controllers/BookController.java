package ru.zimin.springcourse.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.zimin.springcourse.models.Book;
import ru.zimin.springcourse.models.Person;
import ru.zimin.springcourse.services.BookService;
import ru.zimin.springcourse.services.PeopleService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final PeopleService peopleService;

    @Autowired
    public BookController(BookService bookService, PeopleService peopleService) {
        this.bookService = bookService;
        this.peopleService = peopleService;
    }


    @GetMapping("/{offset}")
    public String booksPage(@PathVariable("offset") int pageIndex, Model model) {
        model.addAttribute("books", bookService.findPerPage(pageIndex, 3));
        model.addAttribute("pageIndex", pageIndex);
        return "books/indexBooks";
    }

    @GetMapping("/book/{id}")
    public String show(@PathVariable("id") int id, Model model) throws SQLException {

        Book book = bookService.findById(id);

        model.addAttribute("book", book);
        model.addAttribute("people", peopleService.findAll());
        if (book.getOwner() != null) {
            model.addAttribute("reader", book.getOwner());
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
        bookService.save(book);
        return "redirect:/books/0";

    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookService.findById(id));
        return "books/editBook";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute @Valid Book book,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {

        if (bindingResult.hasErrors()) {
            return "books/editBook";
        }

        bookService.update(id, book);

        return "redirect:/books/0";

    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {

        bookService.remove(id);

        return "redirect:/books/0";
    }

    @PostMapping("/{book_id}/person")
    public String designateBook(@PathVariable("book_id") int book_id,
                                @RequestParam("owner") int owner_id) {

        bookService.updatePersonId(book_id, owner_id);

        return "redirect:/books/0";

    }

    @PatchMapping("/{book_id}/reader")
    public String freeBook(@PathVariable("book_id") int book_id) {

        bookService.updatePersonId(book_id, null);

        return "redirect:/books/0" + book_id;
    }

    @GetMapping("/search")
    public String searchPage(Model model) {

        if (!model.containsAttribute("books")) {
            model.addAttribute("books", new ArrayList<>());
        }
        return "books/search";

    }
    @PostMapping("/find")
    public String search(@RequestParam("name") String name, Model model) {

        List<Book> books = bookService.findByName(name);
        model.addAttribute("books", books);
        searchPage(model);
        return "books/search";
    }


}