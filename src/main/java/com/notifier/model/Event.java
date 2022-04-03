package com.notifier.model;

import com.notifier.web.utils.Status;
import com.vladmihalcea.hibernate.type.interval.PostgreSQLIntervalType;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.TypeDef;
import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@Entity
@TypeDef(
        typeClass = PostgreSQLIntervalType.class,
        defaultForType = Duration.class
)
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String text;

    @Column(columnDefinition = "interval")
    private Duration duration = Duration.ofDays(7);

    @Column(name = "period_time_notification", columnDefinition = "interval")
    private Duration periodTimeNotification = Duration.ofDays(7);
    private Boolean repeatable;
    private LocalDateTime nextExecution;

    @Enumerated(value = EnumType.STRING)
    private Status status = Status.ACTIVE;

    public LocalDateTime notificationTime(){
        return this.nextExecution.minusMinutes(this.duration.toMinutes());
    }
}
