package com.notifier.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Template{
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade = CascadeType.ALL) //каскадные действия
    private List<Timetable> timetableList;
}
