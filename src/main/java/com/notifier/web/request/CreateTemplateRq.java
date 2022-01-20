package com.notifier.web.request;

import com.notifier.model.Event;
import com.notifier.model.Person;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CreateTemplateRq {
    private String name;
    private List<EventRq> events;

    @Data
    public static class EventRq{
        private String text;
        private Duration duration;
        private Boolean repeatable;
        private LocalDateTime nextExecution;

        public Event toEntity(){
            return new Event()
                    .setText(text)
                    .setDuration(duration)
                    .setRepeatable(repeatable)
                    .setNextExecution(nextExecution);
        }
    }
}
