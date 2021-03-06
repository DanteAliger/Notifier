package com.notifier.service;

import com.notifier.exception.NotifierException;
import com.notifier.model.Person;
import com.notifier.repository.PersonRepository;
import com.notifier.web.request.SavePersonRq;
import com.notifier.web.response.PersonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import static com.notifier.exception.ErrorCode.*;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;


    public Person create(SavePersonRq request) throws NotifierException {
        if (!personRepository.findByIdTelegram(request.getIdTelegram()).isPresent()) {
            Person person = new Person();
            person.setName(request.getName());
            person.setSurname(request.getSurname());
            person.setPhone(request.getPhone());
            person.setEmail(request.getEmail());
            person.setIdTelegram(request.getIdTelegram());
            return personRepository.save(person);
        } else {
            throw new NotifierException(USER_EXISTS, HttpStatus.CONFLICT);
        }
    }

    public void delete(Long id) throws NotifierException {
        if (personRepository.findByIdTelegram(id).isPresent()) {
            personRepository.deleteByIdTelegram(id);
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

    public PersonResponse get(Long id) throws NotifierException {
            return personRepository.findByIdTelegram(id).map(PersonResponse::getIdTelegramResponse).orElseThrow(() -> new NotifierException(NOT_FOUND, HttpStatus.NOT_FOUND));
        }

    public Person update(Long id,SavePersonRq request) throws NotifierException{
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new NotifierException(USER_NOT_FOUND,HttpStatus.NOT_FOUND));
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
