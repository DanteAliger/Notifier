package com.notifier.web.request;

import lombok.Data;

import java.util.List;

@Data
public class CreateTemplateRq {
    private String name;
    private List<CreateEventRq> events;


}
