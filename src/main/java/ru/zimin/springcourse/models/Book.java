package ru.zimin.springcourse.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.lang.Nullable;

public class Book {

    private int book_id;

    private Integer person_id;

    @NotEmpty(message = "Name should not be empty")
    private String name;

    @NotEmpty(message = "Author name should not be empty")
    private String author;

    @Min(value = 1, message = "Only after Christ")
    private int yearRelease;

    public Book(int book_id, Integer person_id, String name, String author, int yearRelease) {
        this.book_id = book_id;
        this.person_id = person_id;
        this.name = name;
        this.author = author;
        this.yearRelease = yearRelease;
    }

    public Book() {}

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearRelease() {
        return yearRelease;
    }

    public void setYearRelease(int yearRelease) {
        this.yearRelease = yearRelease;
    }
}
