package com.notifier.repository;

import com.notifier.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("select e from Template t join t.events e where e.id = :eventId and t.id = :templateId")
    Optional<Event> findTemplateEvent(Long templateId, Long eventId);

    @Query(value = "select e.* from event e " +
            "join template on e.id_template = template.id " +
            "join person on template.id_person = person.id " +
            "where person.status='ACTIVE' " +
            "and template.status='ACTIVE' " +
            "and e.status='ACTIVE' " +
            "and (e.next_execution - e.period_time_notification) < CURRENT_TIMESTAMP" , nativeQuery = true )
    List<Event> findNotificationEvents();
}
//"select e from event e " +
//        "join template on e.id_template = template.id " +
//        "join person on template.id_person = person.id " +
//        "where person.status='ACTIVE' " +
//        "and template.status='ACTIVE' " +
//        "and e.status='ACTIVE' " +
//        "and (e.next_execution - e.period_time_notification) < CURRENT_TIMESTAMP" , nativeQuery = true )