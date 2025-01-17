package ru.zimin.springcourse.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.zimin.springcourse.dao.PersonDAO;
import ru.zimin.springcourse.models.Person;

import java.util.Optional;

@Component
public class PersonValidator implements Validator {

    private PersonDAO personDAO;
    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    //проверка для какого класса этот валидатор будет работать
    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        Optional<Person> foundPerson = personDAO.show(person.getEmail());

        if (foundPerson.isPresent()) {
            errors.rejectValue("email", "500", "This email is already used");
        }

    }
}
