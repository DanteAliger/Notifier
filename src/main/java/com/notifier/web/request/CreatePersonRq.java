package com.notifier.web.request;

import lombok.Data;

@Data // создаёт коструктор() required argument, get set toString equals HashCode
public class CreatePersonRq {
    private String name;
    private String surname;
    private String phone;
    private String email;
}
