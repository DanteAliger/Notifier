package com.notifier.web.response;


import com.notifier.model.Event;
import com.notifier.web.utils.Status;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class EventResponse {

    private Long id;
    private String text;
    private Duration periodTimeNotification;
    private Boolean repeatable;
    private LocalDateTime nextExecution;
    private Status status;


    public static EventResponse toEntity(Event event){
        return new EventResponse()
                .setId(event.getId())
                .setText(event.getText())
                .setPeriodTimeNotification(event.getPeriodTimeNotification())
                .setRepeatable(event.getRepeatable())
                .setNextExecution(event.getNextExecution())
                .setStatus(event.getStatus());
    }
}
