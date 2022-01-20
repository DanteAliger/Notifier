package com.notifier.web;

import com.notifier.exception.ErrorResponse;
import com.notifier.exception.NotifierException;
import com.notifier.model.Person;
import com.notifier.model.Template;
import com.notifier.service.PersonService;
import com.notifier.web.request.CreatePersonRq;
import com.notifier.web.request.CreateTemplateRq;
import com.notifier.web.request.UpdatePersonRq;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<?> get(@RequestParam String name) throws NotifierException{
            return ResponseEntity.ok(personService.get(name));
    }

    @PostMapping("/create") //localhost:8081/persons/create
    public ResponseEntity<String> create(@RequestBody CreatePersonRq request) throws NotifierException {
        return ResponseEntity.ok("Hi " + personService.create(request).getName());
    }
    @PostMapping("/{id}/templates/create")
    public ResponseEntity<String> createTemplate(@PathVariable Long id, @RequestBody CreateTemplateRq request) throws NotifierException {
        Template template = personService.createTemplate(id, request);
        return ResponseEntity.ok("Timetable " + template.getName() + " added");
    }

    @GetMapping("/all")
    public ResponseEntity<Set<Person>> all() {
        return ResponseEntity.ok(personService.all());
    }

    @DeleteMapping("/deleteId")
    public ResponseEntity<?> deleteId(@RequestParam Long Id) throws NotifierException{
            personService.delete(Id);
            return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Person> update(@PathVariable Long id, @RequestBody UpdatePersonRq request) {
        return ResponseEntity.ok(personService.update(id ,request));
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAll() {
        personService.delete();
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(value = NotifierException.class) // обработка исключения
    public ResponseEntity<ErrorResponse> handle(NotifierException e) {
        return ResponseEntity.status(e.getStatus()).body(new ErrorResponse(e.getCode()));
    }
}
