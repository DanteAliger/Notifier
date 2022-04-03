package com.notifier.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.notifier.web.utils.Status;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Template{
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_person")
    @JsonIgnore
    private Person person;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_template")
    private List<Event> events = new ArrayList<>();

    @Enumerated(value = EnumType.STRING) // записывает в БД типом varchar(string)
    private Status status = Status.ACTIVE;

    public void addEvent(Event event){
        events.add(event);
    }
}
