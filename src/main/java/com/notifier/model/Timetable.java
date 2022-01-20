package com.notifier.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Timetable {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_event")
    private Event event;
    private LocalDateTime dateTime;

}
