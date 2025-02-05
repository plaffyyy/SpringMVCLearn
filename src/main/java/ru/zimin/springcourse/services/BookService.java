package ru.zimin.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zimin.springcourse.models.Book;
import ru.zimin.springcourse.repositories.BookRepository;
import ru.zimin.springcourse.repositories.PeopleRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;
    private final PeopleService peopleService;
    @Autowired
    public BookService(BookRepository bookRepository, PeopleService peopleService) {
        this.bookRepository = bookRepository;
        this.peopleService = peopleService;
    }

    public List<Book> findByName(String name) {
        return bookRepository.findByNameStartingWithOrderByYearRelease(name);
    }

    public List<Book> findPerPage(int indexPage, int itemsPerPage) {
        if (indexPage < 0) {
            indexPage = 0;
        }
        return bookRepository.findAll(PageRequest.of(indexPage, itemsPerPage, Sort.by("yearRelease"))).getContent();
    }

    public Book findById(Integer id) {
        return bookRepository.findById(id).orElse(null);
    }
    @Transactional
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public void update(int id, Book book) {
        Book bookUpdated = findById(id);

        bookUpdated.setAuthor(book.getAuthor());
        bookUpdated.setName(book.getName());
        bookUpdated.setYearRelease(book.getYearRelease());
    }
    @Transactional
    public void remove(int id) {
        bookRepository.removeById(id);
    }
    @Transactional
    public void updatePersonId(int id, Integer personId) {
        Book book = findById(id);
        if (personId != null) {
            book.setOwner(peopleService.findOne(personId));
        } else {
            book.setOwner(null);
        }
        bookRepository.save(book);
    }
}
