package com.notifier.repository;

import com.notifier.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("select e from Template t join t.events e where e.id = :eventId and t.id = :templateId")
    Optional<Event> findTemplateEvent(Long templateId, Long eventId);
}