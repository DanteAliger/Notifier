package com.notifier.web;

import com.notifier.exception.ErrorResponse;
import com.notifier.exception.NotifierException;
import com.notifier.model.Event;
import com.notifier.model.Template;
import com.notifier.service.PersonTemplateService;
import com.notifier.web.request.CreateEventRq;
import com.notifier.web.request.CreateTemplateRq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/templates")
public class TemplateController {
    @Autowired
    private PersonTemplateService personTemplateService;

    @PostMapping("/{id}/create")
    public ResponseEntity<String> createTemplate(@PathVariable Long id, @RequestBody CreateTemplateRq request) throws NotifierException {
        Template template = personTemplateService.createTemplate(id, request);
        return ResponseEntity.ok("Timetable " + template.getName() + " added");
    }

    @DeleteMapping("/{tId}/delete")
    public ResponseEntity<String> deleteTemplate(@PathVariable Long tId ) throws NotifierException {
        return ResponseEntity.ok("Template delete: " + personTemplateService.deleteTemplate(tId));
    }

    @PostMapping("/{id}/event/create")
    public ResponseEntity<String> createEvent(@PathVariable Long id, @RequestBody CreateEventRq request) throws NotifierException{
        Event event = personTemplateService.createEvent(id, request);
        return ResponseEntity.ok("Event add: " + event.getText());
    }

    @PutMapping("/{tId}/event/{eId}/update")
    public ResponseEntity<Event> updateEvent (@PathVariable Long tId,@PathVariable Long eId, @RequestBody CreateEventRq request) throws NotifierException {
        return ResponseEntity.ok(personTemplateService.updateEvent(tId, eId, request));
    }

    @DeleteMapping("/{tId}/event/{eId}/delete")
    public ResponseEntity<String> deleteEvent(@PathVariable Long tId, @PathVariable Long eId ) throws NotifierException {
        return ResponseEntity.ok("Event delete: " + personTemplateService.deleteEvent(tId,eId));
    }

    @ExceptionHandler(value = NotifierException.class) // обработка исключения
    public ResponseEntity<ErrorResponse> handle(NotifierException e) {
        return ResponseEntity.status(e.getStatus()).body(new ErrorResponse(e.getCode()));
    }
}
