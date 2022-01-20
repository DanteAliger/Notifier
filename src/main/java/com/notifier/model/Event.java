package com.notifier.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Duration;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@Entity
//@ManyToOne
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String text;
    private Duration duration;
    private Boolean repeatable;
    private LocalDateTime nextExecution;
    private Status status = Status.ACTIVE;

    public enum Status{
        ACTIVE,
        COMPLETED
    }
}
