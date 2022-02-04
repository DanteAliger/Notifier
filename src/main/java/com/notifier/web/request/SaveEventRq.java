package com.notifier.web.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.notifier.model.Event;
import com.notifier.web.request.validation.DataCheck;
import com.notifier.web.request.validation.One;
import com.notifier.web.request.validation.Two;
import com.notifier.web.transformer.DurationMinutesDeserializer;
import com.notifier.web.utils.Constants;
import lombok.Data;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;
import java.time.Duration;
import java.time.LocalDateTime;


@Data
public class SaveEventRq {
        @NotNull(message = Constants.NOT_NULL, groups = One.class)
        @Size(min = 1, max = 255, message = Constants.INVALID, groups = Two.class)
        private String text;

        @NotNull(message = Constants.NOT_NULL )
        @JsonDeserialize(using  = DurationMinutesDeserializer.class)
        private Duration periodTimeNotification;

        @NotNull(message = Constants.NOT_NULL)
        private Boolean repeatable;

        @NotNull(message = Constants.NOT_NULL, groups = One.class)
        @DataCheck(message = Constants.INVALID_DATE, groups = Two.class)
        private LocalDateTime nextExecution;

        public Event toEntity(){
            return new Event()
                    .setText(text)
                    .setPeriodTimeNotification(periodTimeNotification)
                    .setRepeatable(repeatable)
                    .setNextExecution(nextExecution);
        }
}
