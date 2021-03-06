package com.notifier.web;

import com.notifier.exception.ErrorResponse;
import com.notifier.exception.NotifierException;
import com.notifier.exception.ValidationErrorResponse;
import com.notifier.model.Person;
import com.notifier.service.PersonService;
import com.notifier.web.request.SavePersonRq;
import com.notifier.web.request.validation.ValidationGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Validated
@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/getIdTelegram")
    public ResponseEntity<?> getIdTelegram(@RequestParam Long idTelegram) throws NotifierException{
        return ResponseEntity.ok(personService.get(idTelegram));
    }

    @PostMapping("/create") //localhost:8081/persons/create
    public ResponseEntity<?> create(@RequestBody @Validated(ValidationGroup.class) SavePersonRq request) throws NotifierException {
        personService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/all")
    public ResponseEntity<Set<Person>> all() {
        return ResponseEntity.ok(personService.all());
    }


    @DeleteMapping("/deleteId")
    public ResponseEntity<?> deleteId( @RequestParam Long Id) throws NotifierException{
            personService.delete(Id);
            return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Person> update(@PathVariable Long id, @RequestBody @Validated(ValidationGroup.class) SavePersonRq request) throws NotifierException {
        return ResponseEntity.ok(personService.update(id ,request));
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAll() {
        personService.delete();
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(value = NotifierException.class) // ?????????????????? ????????????????????
    public ResponseEntity<ErrorResponse> handle(NotifierException e) {
        return ResponseEntity.status(e.getStatus()).body(new ErrorResponse(e.getCode()));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class) // ?????????????????? ????????????????????
    public ResponseEntity<ValidationErrorResponse> handle(MethodArgumentNotValidException e) {
        List<ValidationErrorResponse.Error> errors = e.getBindingResult().getFieldErrors().stream()
                .map(s -> new ValidationErrorResponse.Error(s.getField(), s.getDefaultMessage()))
                .collect(Collectors.toList());
        ValidationErrorResponse body = new ValidationErrorResponse(errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
}
