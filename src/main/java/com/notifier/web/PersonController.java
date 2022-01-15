package com.notifier.web;

import com.notifier.model.Person;
import com.notifier.repository.PersonRepository;
import com.notifier.web.request.CreatePersonRq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;
    //get post put delete

    @GetMapping("/get")
    public ResponseEntity<Person> get(@RequestParam String name){
        Person person = personRepository.findByName(name);
        return ResponseEntity.ok(person);
    }

    @PostMapping("/create") //localhost:8081/persons/create
    public ResponseEntity<String> create(@RequestBody CreatePersonRq request) {
        Person person = new Person();
        person.setName(request.getName());
        personRepository.save(person);
        return ResponseEntity.ok("Hi " + request.getName());
    }
}
