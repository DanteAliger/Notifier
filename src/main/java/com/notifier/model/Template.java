package com.notifier.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToMany(cascade = CascadeType.ALL) //каскадные действия
    @JoinColumn(name = "id_template")
    private List<Event> events = new ArrayList<>();

    public void addEvent(Event event){
        events.add(event);
    }
}
