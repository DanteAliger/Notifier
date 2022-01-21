package com.notifier.web.request;

import com.notifier.model.Event;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;


@Data
public class CreateEventRq {
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
