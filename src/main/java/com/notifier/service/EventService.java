package com.notifier.service;

import com.notifier.dto.NotificationDTO;
import com.notifier.exception.NotifierException;
import com.notifier.model.Event;
import com.notifier.model.Person;
import com.notifier.model.Template;
import com.notifier.repository.EventRepository;
import com.notifier.repository.PersonRepository;
import com.notifier.repository.TemplateRepository;
import com.notifier.web.request.SaveEventRq;
import com.notifier.web.response.EventResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.notifier.exception.ErrorCode.NOT_FOUND;
import static com.notifier.exception.ErrorCode.USER_NOT_FOUND;

@Slf4j
@Service
public class EventService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private EventRepository eventRepository;

    public Event createEvent(Long idTelegram ,String nameTemplate, SaveEventRq request) throws NotifierException {
        log.info("Создание события для пользователя.");
        Person person = personRepository.findByIdTelegram(idTelegram).orElseThrow(() -> new NotifierException(USER_NOT_FOUND, HttpStatus.NOT_FOUND));
        Template template = person.getTemplates().stream().filter(t -> t.getName().equals(nameTemplate)).findFirst().orElseThrow(() -> new NotifierException(USER_NOT_FOUND, HttpStatus.NOT_FOUND));
        template.addEvent(request.toEntity());
        templateRepository.save(template);
        return request.toEntity();
    }

    @Transactional
    public String deleteEvent(Long idTelegram ,Long idTemplate, Long idEvent) throws NotifierException {
        log.info("Удаление события из БД:");
        Person person = personRepository.findByIdTelegram(idTelegram).orElseThrow(() -> new NotifierException(USER_NOT_FOUND, HttpStatus.NOT_FOUND));
        Template template = person.getTemplates().stream().filter(t -> t.getId().equals(idTemplate)).findFirst().orElseThrow(() -> new NotifierException(USER_NOT_FOUND, HttpStatus.NOT_FOUND));
        Event event = template.getEvents().stream().filter(e -> idEvent.equals(e.getId())).findFirst().orElseThrow(() -> new NotifierException(USER_NOT_FOUND, HttpStatus.NOT_FOUND));
        eventRepository.deleteById(idEvent);
        return event.getText();
    }


    public List<EventResponse> all(Long idTelegram, String nameTemplate) throws NotifierException {
        log.info("Возвращение списка событий");
        Person person = personRepository.findByIdTelegram(idTelegram).orElseThrow(() -> new NotifierException(USER_NOT_FOUND, HttpStatus.NOT_FOUND));
        Template template = person.getTemplates().stream().filter(t -> t.getName().equals(nameTemplate)).findFirst().orElseThrow(() -> new NotifierException(USER_NOT_FOUND, HttpStatus.NOT_FOUND));
        List<EventResponse> eventAll = new ArrayList<>();
        eventAll.addAll(template.getEvents().stream().map(e-> EventResponse.toEntity(e)).collect(Collectors.toList()));
        return eventAll;
    }


    public Event updateEvent(Long templateId, Long eventId, SaveEventRq request) throws NotifierException {
        log.info("Изменение события");
        Event event = eventRepository.findTemplateEvent(templateId, eventId).orElseThrow(()-> new NotifierException(NOT_FOUND, HttpStatus.NOT_FOUND));
        event.setText(request.getText());
        event.setNextExecution(request.getNextExecution());
        event.setRepeatable(request.getRepeatable());
        eventRepository.save(event);
        return event;
    }

    public List<NotificationDTO> findNotificationDTO() {
        log.info("Возвращение листа NotificationDTO");
        return eventRepository.findNotificationDTO();
    }

    @Transactional
    public void updateAfterNotify(Long eventId){
        eventRepository.updateAfterNotify(eventId);
    }

    public synchronized void saveAllEvents(List<Event> events){
        synchronized (this){
            eventRepository.saveAll(events);
        }

    }
}
