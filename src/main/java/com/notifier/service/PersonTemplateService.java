package com.notifier.service;

import com.notifier.exception.NotifierException;
import com.notifier.model.Event;
import com.notifier.model.Person;
import com.notifier.model.Template;
import com.notifier.repository.EventRepository;
import com.notifier.repository.PersonRepository;
import com.notifier.repository.TemplateRepository;
import com.notifier.web.request.SaveEventRq;
import com.notifier.web.request.SaveTemplateRq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static com.notifier.exception.ErrorCode.NOT_FOUND;
import static com.notifier.exception.ErrorCode.USER_NOT_FOUND;

@Service
public class PersonTemplateService {

    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private PersonRepository personRepository;

    public Template createTemplate(Long personId, SaveTemplateRq request) throws NotifierException {
        Person person = personRepository.findById(personId).orElseThrow(() -> new NotifierException(USER_NOT_FOUND, HttpStatus.NOT_FOUND));
        Template template = new Template();
        template.setPerson(person);
        template.setName(request.getName());
        if(!CollectionUtils.isEmpty(request.getEvents())){
            request.getEvents().forEach((event) -> template.addEvent(event.toEntity()));
        }
        return templateRepository.save(template);

//        for (CreateTemplateRq.EventRq event: request.getEvents()) {
//            template.getEvents().add(event.toEntity());
//        }
//        throw new NotifierException(USER_EXISTS, HttpStatus.CONFLICT);

    }

    public Event createEvent(Long templateId, SaveEventRq request) throws NotifierException{
        Template template = templateRepository.findById(templateId).orElseThrow(() -> new NotifierException(NOT_FOUND, HttpStatus.NOT_FOUND));
        template.addEvent(request.toEntity());
        templateRepository.save(template);
        return request.toEntity();
    }

    public Event updateEvent(Long templateId, Long eventId, SaveEventRq request) throws NotifierException {
        Event event = eventRepository.findTemplateEvent(templateId, eventId).orElseThrow(()-> new NotifierException(NOT_FOUND, HttpStatus.NOT_FOUND));
        event.setText(request.getText());
        event.setDuration(request.getDuration());
        event.setNextExecution(request.getNextExecution());
        event.setRepeatable(request.getRepeatable());
        return event;
    }

    public String deleteEvent(Long tId, Long eId) throws NotifierException {
        Event event = eventRepository.findTemplateEvent(tId,eId).orElseThrow(() -> new NotifierException(NOT_FOUND, HttpStatus.NOT_FOUND));
        eventRepository.delete(event);
        return event.getText();
    }

    public String deleteTemplate(Long tId) throws NotifierException {
        Template template = templateRepository.findById(tId).orElseThrow(() -> new NotifierException(NOT_FOUND, HttpStatus.NOT_FOUND));
        templateRepository.delete(template);
        return template.getName();
    }

    public List<Event> findNotificationEvents() {
        return eventRepository.findNotificationEvents();
    }

    public void saveAllEvents(List<Event> events){
        eventRepository.saveAll(events);
    }
}
