package ru.zimin.springcourse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zimin.springcourse.models.Person;

public interface PeopleRepository extends JpaRepository<Person, Integer> {
}
