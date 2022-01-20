package com.notifier.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Template{
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_person")

    private Person person;

    @OneToMany(cascade = CascadeType.ALL) //каскадные действия
    @JoinColumn(name = "id_timetable")
    @JsonIgnore
    private List<Timetable> timetableList;
}
