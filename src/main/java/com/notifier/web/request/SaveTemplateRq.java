package com.notifier.web.request;

import lombok.Data;

import java.util.List;

@Data
public class SaveTemplateRq {
    private String name;
    private List<SaveEventRq> events;


}
