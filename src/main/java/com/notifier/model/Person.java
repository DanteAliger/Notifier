package com.notifier.model;

import com.notifier.web.utils.Status;
import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Data
@Entity // создание сущности
public class Person{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;
    private String phone;
    private String email;
    @OneToMany(mappedBy = "person")
    private List<Template> templates;

    @Enumerated(value = EnumType.STRING)
    private Status status = Status.ACTIVE;

}
