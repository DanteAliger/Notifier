package com.notifier.repository;

import com.notifier.dto.NotificationDTO;
import com.notifier.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("select e from Template t join t.events e where e.id = :eventId and t.id = :templateId")
    Optional<Event> findTemplateEvent(Long templateId, Long eventId);

    @Query(value = "select p.id_telegram as idTelegram, e.id as idEvent, e.text as textEvent from Event e " +
            "join Template t on e.id_template = t.id " +
            "join Person p on t.id_person = p.id " +
            "where p.status='ACTIVE' " +
            "and t.status='ACTIVE' " +
            "and e.status='ACTIVE' " +
            "and e.next_execution < CURRENT_TIMESTAMP ", nativeQuery = true)
    List<NotificationDTO> findNotificationDTO();


    @Modifying
    @Query(value =
            "update event set " +
                    " next_execution =  case when (repeatable = true) then next_execution + duration else next_execution end," +
                    " status =  case when ( repeatable = false) then 'COMPLETED' else 'ACTIVE' end" +
                    " where id = :eId " ,nativeQuery = true)
    void updateAfterNotify(Long eId);

    @Modifying
    @Query("delete from Event e where e.id = :id")
    void deleteById(Long id);

}

