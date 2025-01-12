package ru.zimin.springcourse.dao;

import org.springframework.stereotype.Component;
import ru.zimin.springcourse.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_CNT = 0;
    private List<Person> people;

    {
        people = new ArrayList<>();
        people.add(new Person(++PEOPLE_CNT, "Kostya"));
        people.add(new Person(++PEOPLE_CNT, "Ivan"));
        people.add(new Person(++PEOPLE_CNT, "Julia"));
        people.add(new Person(++PEOPLE_CNT, "Sigma"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream()
                .filter(person -> person.getId() == id)
                .findAny()
                .orElse(null);
    }
}
