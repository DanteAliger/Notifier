package com.notifier.web;

import com.notifier.exception.NotFoundException;
import com.notifier.exception.UserExistsException;
import com.notifier.model.Person;
import com.notifier.service.PersonService;
import com.notifier.web.request.CreatePersonRq;
import com.notifier.web.request.UpdatePersonRq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonService personService;
    //get post put delete

    @GetMapping("/get")
    public ResponseEntity<?> get(@RequestParam String name) {
        try {
            return ResponseEntity.ok(personService.get(name));
        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found this person!");
        }
    }

    @PostMapping("/create") //localhost:8081/persons/create
    public ResponseEntity<String> create(@RequestBody CreatePersonRq request) {
        try {
            return ResponseEntity.ok("Hi " + personService.create(request).getName());
        } catch (UserExistsException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Person with name = " + request.getName() + " already exists");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Set<Person>> all() {
        return ResponseEntity.ok(personService.all());
    }

    @DeleteMapping("/deleteId")
    public ResponseEntity<?> deleteId(@RequestParam Long Id) {
        try {
            personService.delete(Id);
            return ResponseEntity.ok().build();
        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found this person!");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Person> update(@RequestBody UpdatePersonRq request) {
        return ResponseEntity.ok(personService.update(request));
    }
}
