package com.notifier.service;

import com.notifier.exception.NotifierException;
import com.notifier.model.Event;
import com.notifier.model.Person;
import com.notifier.model.Template;
import com.notifier.repository.EventRepository;
import com.notifier.repository.PersonRepository;
import com.notifier.repository.TemplateRepository;
import com.notifier.web.request.SaveTemplateRq;
import com.notifier.web.response.TemplateResponse;
import com.notifier.web.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.notifier.exception.ErrorCode.*;

@Service
public class TemplateService {

    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private PersonRepository personRepository;

    public TemplateResponse createTemplate(Long idTelegram, SaveTemplateRq request) throws NotifierException {
        Person person = personRepository.findByIdTelegram(idTelegram).orElseThrow(() -> new NotifierException(USER_NOT_FOUND, HttpStatus.NOT_FOUND));
        if(person.getTemplates().stream().noneMatch(t -> t.getName().equals(request.getName()))) {
            Template template = new Template();
            template.setPerson(person);
            template.setName(request.getName());
            templateRepository.save(template);
            return new TemplateResponse(template.getId(), template.getName(), template.getStatus());
        } else
            throw new NotifierException(TIMETABLE_EXISTS, HttpStatus.CONFLICT);
    }

    public String deleteTemplate(Long idTelegram, String nameT) throws NotifierException {
        Person person = personRepository.findByIdTelegram(idTelegram).orElseThrow(() -> new NotifierException(USER_NOT_FOUND, HttpStatus.NOT_FOUND));
        Template template = person.getTemplates().stream().filter(t-> t.getName().equals(nameT)).findFirst().get();
        template.getEvents().stream().forEach(e-> eventRepository.delete(e));
        templateRepository.deleteById(template.getId());
        return template.getName();
    }

    public String selectTemplate(Long idTelegram, String nameT) throws NotifierException {
        Person person = personRepository.findByIdTelegram(idTelegram).orElseThrow(() -> new NotifierException(USER_NOT_FOUND, HttpStatus.NOT_FOUND));
        Template template = person.getTemplates().stream().filter(t-> t.getName().equals(nameT)).findFirst().get();
        if (template.getStatus() == Status.ACTIVE)
            template.setStatus(Status.COMPLETED);
        else {
            template.getEvents().forEach(this::updateTime);
            template.setStatus(Status.ACTIVE);
        }
        templateRepository.save(template);
        return template.getName();
    }

    public List<TemplateResponse> all(Long idTelegram) throws NotifierException {
        Person person = personRepository.findByIdTelegram(idTelegram).orElseThrow(() -> new NotifierException(USER_NOT_FOUND, HttpStatus.NOT_FOUND));
        List<TemplateResponse> templatesAll = new ArrayList<>();
        templatesAll.addAll(person.getTemplates().stream().map(t-> new TemplateResponse(t.getId(), t.getName(), t.getStatus())).collect(Collectors.toSet()));
        return templatesAll;
    }

    private void updateTime(Event event){
        while (event.getNextExecution().isAfter(LocalDateTime.now())) {
            event.setNextExecution(event.getNextExecution().plus(event.getDuration()));
        }
    }



}
