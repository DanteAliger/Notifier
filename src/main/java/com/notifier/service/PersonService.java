package com.notifier.service;

import com.notifier.exception.ErrorCode;
import com.notifier.exception.NotFoundException;
import com.notifier.exception.NotifierException;
import com.notifier.exception.UserExistsException;
import com.notifier.model.Person;
import com.notifier.repository.PersonRepository;
import com.notifier.web.request.CreatePersonRq;
import com.notifier.web.request.UpdatePersonRq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.notifier.exception.ErrorCode.NOT_FOUND;
import static com.notifier.exception.ErrorCode.USER_EXISTS;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person create(CreatePersonRq request) throws NotifierException {
        if (personRepository.findByName(request.getName()) == null) {
            Person person = new Person();
            person.setName(request.getName());
            return personRepository.save(person);
        } else {
            throw new NotifierException(USER_EXISTS, HttpStatus.CONFLICT);
        }
    }

    public void delete(Long id) throws NotifierException {
        if (personRepository.findById(id).isPresent()) {
            personRepository.deleteById(id);
        } else {
            throw new NotifierException(NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }

    public Set<Person> all() {
        Comparator<Person> comparator = (o1, o2) -> o1.getName().compareTo(o2.getName());
        Comparator<Person> comparator2 = Comparator.comparing(p -> p.getName());
        Comparator<Person> comparator3 = Comparator.comparing(Person::getName);
        SortedSet<Person> personAll = new TreeSet<>(comparator3);
        personAll.addAll(personRepository.findAll());
        return personAll;
    }

    public Person get(String name) throws NotifierException {
        if (personRepository.findByName(name) != null) {
            return personRepository.findByName(name);
        } else {
            throw new NotifierException(NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }

    public Person update(Long id,UpdatePersonRq request){
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Такого пользователя нет"));
        person.setName(request.getName());
        person.setSurname(request.getSurname());
        person.setPhone(request.getPhone());
        person.setEmail(request.getEmail());
        personRepository.save(person);
        return person;
    }

    public void delete() {
        personRepository.deleteAll();
    }
}
