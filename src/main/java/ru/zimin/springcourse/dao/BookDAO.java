package ru.zimin.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.zimin.springcourse.models.Book;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {

        return jdbcTemplate.query(
                "SELECT * FROM Book",
                new BeanPropertyRowMapper<>(Book.class)
        );

    }

    public Book show(int id) {

        return jdbcTemplate.query(
                "SELECT * FROM Book WHERE book_id=?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)
        ).stream().findAny().orElse(null);

    }

    public void save(Book book) {

        jdbcTemplate.update(
                "INSERT INTO Book(person_id, name, author, year_release) values (null, ?, ?, ?)",
                book.getName(), book.getAuthor(), book.getYearRelease()
        );

    }

    public void update(int id, Book book) {

        jdbcTemplate.update(
                "UPDATE Book SET name=?, author=?, year_release=? WHERE book_id=?",
                book.getName(), book.getAuthor(), book.getYearRelease(), id
        );

    }

    public void updatePerson(int id, Integer person_id) {

        jdbcTemplate.update(
                "UPDATE Book SET person_id=? WHERE book_id=?",
                person_id, id
        );

    }

    public void remove(int id) {

        jdbcTemplate.update(
                "DELETE FROM Book WHERE book_id=?",
                id
        );

    }

    public List<Book> getAll(int person_id) {

        return jdbcTemplate.query(
                "SELECT * FROM Book WHERE person_id=?",
                new Object[]{person_id},
                new BeanPropertyRowMapper<>(Book.class)
        );
    }
}
