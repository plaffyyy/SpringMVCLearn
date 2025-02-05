package ru.zimin.springcourse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zimin.springcourse.models.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    void removeById(int id);

    List<Book> findByNameStartingWithOrderByYearRelease(String name);
}
