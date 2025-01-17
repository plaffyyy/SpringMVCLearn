package ru.zimin.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.zimin.springcourse.models.Person;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() throws SQLException {
        //new BeanPropertyRowMapper создает бин объекта из которого нам нужен маппер
        //jdbc делает это за нас
        return jdbcTemplate.query(
                "SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class)

        );
    }

    public Person show(int id) {

        return (Person) jdbcTemplate.query(
                "SELECT * FROM Person WHERE id=?",
                new Object[]{id},
                new PersonMapper())
                    .stream().findAny()
                    .orElse(null);

    }
    //two different ways: using BeanPropertyRowMapper and own Mapper

    public Optional<Person> show(String email) {
        return jdbcTemplate.query(
                "SELECT * FROM Person WHERE email=?",
                new Object[]{email},
                new BeanPropertyRowMapper<>(Person.class)
        ).stream().findAny();
    }

    public void save(Person person) {
//        person.setId(++PEOPLE_CNT);
//        people.add(person);

        jdbcTemplate.update(
                "INSERT INTO Person(name, age, email, address) Values(?, ?, ?, ?)",
                person.getName(), person.getAge(), person.getEmail(), person.getAddress()
        );

    }

    public void update(int id, Person person) {

        jdbcTemplate.update(
                "UPDATE Person SET name=?, age=?, email=?, address=? WHERE id=?",
                person.getName(), person.getAge(), person.getEmail(), person.getAddress(), id
        );

    }

    public void delete(int id) {

        jdbcTemplate.update(
                "DELETE FROM Person WHERE id=?",
                id
        );

    }
}
