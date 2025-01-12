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
        people.add(new Person(++PEOPLE_CNT, "Kostya", 19, "k.zimin@innopolis.university"));
        people.add(new Person(++PEOPLE_CNT, "Ivan", 52, "asdasd"));
        people.add(new Person(++PEOPLE_CNT, "Julia", 19, "asd"));
        people.add(new Person(++PEOPLE_CNT, "Sigma", -10, "sigmaboy228@mail.ru"));
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

    public void save(Person person) {
        person.setId(++PEOPLE_CNT);
        people.add(person);
    }

    public void update(int id, Person person) {
        Person personToBeUpdated = show(id);

        personToBeUpdated.setName(person.getName());
        personToBeUpdated.setAge(person.getAge());
        personToBeUpdated.setEmail(person.getEmail());
    }

    public void delete(int id) {
        Person personToBeDeleted = show(id);

        people.remove(personToBeDeleted);
    }
}
