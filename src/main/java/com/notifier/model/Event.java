package com.notifier.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true) // сhain - это цепочка, создает ципочку из setterov and getterov, то есть сделать в одну строку всё
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
    @Enumerated(value = EnumType.STRING)
    private Status status = Status.ACTIVE;

    public enum Status{
        COMPLETED,
        ACTIVE

    }
}
