package com.notifier.web.request;

import com.notifier.model.Event;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CreateTemplateRq {
    private String name;
    private List<CreateEventRq> events;


}
