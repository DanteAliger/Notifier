package com.notifier.web;

import com.notifier.exception.ErrorResponse;
import com.notifier.exception.NotifierException;
import com.notifier.exception.ValidationErrorResponse;
import com.notifier.model.Event;
import com.notifier.service.EventService;
import com.notifier.web.request.SaveEventRq;
import com.notifier.web.request.validation.ValidationGroup;
import com.notifier.web.response.EventResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/persons/{idTelegram}/template/{nameTemplate}/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/create")
    public ResponseEntity<?> createEvent(@PathVariable Long idTelegram, @PathVariable String nameTemplate, @RequestBody @Validated(ValidationGroup.class) SaveEventRq request) throws NotifierException {
        eventService.createEvent(idTelegram, nameTemplate, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteEvent(@PathVariable Long idTelegram, @PathVariable Long nameTemplate , @RequestParam Long idEvent) throws NotifierException {
        return ResponseEntity.ok("Event delete: " + eventService.deleteEvent(idTelegram,nameTemplate,idEvent));
    }

    @GetMapping("/all")
    public ResponseEntity<List<EventResponse>> all(@PathVariable Long idTelegram, @PathVariable String nameTemplate) throws NotifierException {
        return ResponseEntity.ok(eventService.all(idTelegram, nameTemplate));
    }

    @PutMapping("/{idEvent}/update")
    public ResponseEntity<Event> updateEvent (@PathVariable Long tId,@PathVariable Long eId, @RequestBody @Validated(ValidationGroup.class) SaveEventRq request) throws NotifierException {
        return ResponseEntity.ok(eventService.updateEvent(tId, eId, request));
    }


    @ExceptionHandler(value = NotifierException.class) // обработка исключения
    public ResponseEntity<ErrorResponse> handle(NotifierException e) {

        return ResponseEntity.status(e.getStatus()).body(new ErrorResponse(e.getCode()));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class) // обработка исключения
    public ResponseEntity<ValidationErrorResponse> handle(MethodArgumentNotValidException e) {
        log.error("Validation error", e);
        List<ValidationErrorResponse.Error> errors = e.getBindingResult().getFieldErrors().stream()
                .map(s -> new ValidationErrorResponse.Error(s.getField(), s.getDefaultMessage()))
                .collect(Collectors.toList());
        ValidationErrorResponse body = new ValidationErrorResponse(errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
}
