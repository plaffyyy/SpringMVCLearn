//package ru.zimin.springcourse.dao;
//
//import jakarta.persistence.Query;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//import ru.zimin.springcourse.models.Person;
//
//import java.util.List;
//
///**
// * @author Neil Alishev
// */
//
//@Component
//public class PersonDAO {
//
//    private final SessionFactory sessionFactory;
//
//    @Autowired
//    public PersonDAO(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    @Transactional(readOnly = true)
//    public List<Person> index() {
//
//        Session session = sessionFactory.getCurrentSession();
//
//        return session.createQuery("select p from Person p", Person.class)
//                .getResultList();
//
//    }
//    @Transactional(readOnly = true)
//    public Person show(int id) {
//        Session session = sessionFactory.getCurrentSession();
//        return session.get(Person.class, id);
//    }
//    @Transactional
//    public void save(Person person) {
//        Session session = sessionFactory.getCurrentSession();
//
//        session.save(person);
//
//
//    }
//    @Transactional
//    public void update(int id, Person updatedPerson) {
//
//        Session session = sessionFactory.getCurrentSession();
//        //также можно сделать просто через сеттеры
//
//        Query query = session.createQuery(
//                "update Person set name= :name, age= :age, email= :email "
//                        + "where id = :id"
//        );
//
//        query.setParameter("name", updatedPerson.getName());
//        query.setParameter("age", updatedPerson.getAge());
//        query.setParameter("email", updatedPerson.getEmail());
//        query.setParameter("id", id);
//
//        query.executeUpdate();
//
//
//    }
//    @Transactional
//    public void delete(int id) {
//
//        Session session = sessionFactory.getCurrentSession();
//
//        session.remove(session.get(Person.class, id));
//
//    }
//}