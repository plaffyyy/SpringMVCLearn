package ru.zimin.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zimin.springcourse.models.Person;
import ru.zimin.springcourse.repositories.PeopleRepository;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findOne(Integer id) {
        return peopleRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Person person) {
        person.setCreateAt(new Date());

        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {

        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);

    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

}
