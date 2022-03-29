package com.notifier.scheduler;

import com.notifier.dto.NotificationDTO;
import com.notifier.service.EventService;
import com.notifier.service.NotificationSchedulerService;
import com.notifier.web.request.NotifyRq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class NotificationScheduler {

    @Autowired
    private EventService eventService;

    @Value(value = "${notification.enabled}")
    private Boolean enabled;

    @Autowired
    private NotificationSchedulerService notifyService;

    @Scheduled(fixedRateString = "${notification.period}", timeUnit = TimeUnit.SECONDS)
    public void run(){
        if (!enabled)
            return;
        log.info("Launch Scheduler");
        eventService.findNotificationDTO().forEach(this::notify);
    }

    private void notify(NotificationDTO event){

        if (notifyService.notifyTelegram(new NotifyRq(event.getIdTelegram(), event.getTextEvent())))
        {
        log.info("Notification: " + event.getTextEvent());
        eventService.updateAfterNotify(event.getIdEvent());
        }
    }

}
