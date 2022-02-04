package com.notifier.web.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.notifier.model.Event;
import com.notifier.web.transformer.DurationMinutesDeserializer;
import com.notifier.web.utils.Constants;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.Duration;
import java.time.LocalDateTime;


@Data
public class SaveEventRq {
        @Size(min = 1, max = 255, message = Constants.INVALID)
        @NotNull(message = Constants.NOT_NULL)
        private String text;

        @NotNull(message = Constants.NOT_NULL)
        @JsonDeserialize(using  = DurationMinutesDeserializer.class)
        private Duration periodTimeNotification;

        @NotNull(message = Constants.NOT_NULL)
        private Boolean repeatable;

        @DataCheck(message = Constants.INVALID_DATE)
        @NotNull(message = Constants.NOT_NULL)
        private LocalDateTime nextExecution;

        public Event toEntity(){
            return new Event()
                    .setText(text)
                    .setPeriodTimeNotification(periodTimeNotification)
                    .setRepeatable(repeatable)
                    .setNextExecution(nextExecution);
        }
}
