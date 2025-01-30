package ru.zimin.springcourse.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.zimin.springcourse.models.Person;

import java.util.List;

/**
 * @author Neil Alishev
 */

@Component
public class PersonDAO {

    private final EntityManager entityManager;

    @Autowired
    public PersonDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    public void testNPlus1() {
        Session session = entityManager.unwrap(Session.class);

        List<Person> people = session.createQuery("select p from Person p", Person.class).getResultList();

        for (Person person: people) {

            System.out.println("Person " + person.getName() + " has " + person.getItems());

        }

    }
    @Transactional(readOnly = true)
    public void fixNPlus1() {

        Session session = entityManager.unwrap(Session.class);

        List<Person> people = session.createQuery("select p from Person p LEFT join fetch p.items").getResultList();

        for (Person person: people) {

            System.out.println("Person " + person.getName() + " has " + person.getItems());

        }


    }
}