package com.notifier.web.request;

import lombok.Data;

@Data // создаёт коструктор() required argument, get set toString equals HashCode
public class CreatePersonRq {
    private String name;
}
