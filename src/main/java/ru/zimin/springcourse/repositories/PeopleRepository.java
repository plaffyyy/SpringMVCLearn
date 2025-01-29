package ru.zimin.springcourse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zimin.springcourse.models.Person;

import java.util.List;

public interface PeopleRepository extends JpaRepository<Person, Integer> {
    List<Person> findByName(String name);

    List<Person> findByNameOrderByAge(String name);

    List<Person> findByNameStartingWith(String name);

    List<Person> findByNameOrEmail(String name, String email);
}
