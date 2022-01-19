package com.notifier.service;

import com.notifier.exception.NotFoundException;
import com.notifier.exception.UserExistsException;
import com.notifier.model.Person;
import com.notifier.repository.PersonRepository;
import com.notifier.web.request.CreatePersonRq;
import com.notifier.web.request.UpdatePersonRq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person create(CreatePersonRq request) throws UserExistsException {
        if (personRepository.findByName(request.getName()) == null) {
            Person person = new Person();
            person.setName(request.getName());
            return personRepository.save(person);
        } else {
            throw new UserExistsException();
        }
    }

    public void delete(Long id) throws NotFoundException {
        if (personRepository.findById(id).isPresent()) {
            personRepository.deleteById(id);
        } else {
            throw new NotFoundException();
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

    public Person get(String name) throws NotFoundException {
        if (personRepository.findByName(name) != null) {
            return personRepository.findByName(name);
        } else {
            throw new NotFoundException();
        }
    }

    public Person update(UpdatePersonRq request){
        Person person = personRepository.findById(request.getId())
                .orElseThrow(() -> new IllegalArgumentException("Такого пользователя нет"));
        person.setName(request.getName());
        personRepository.save(person);
        return person;
    }

    public void delete() {
        personRepository.deleteAll();
    }
}
