package com.notifier.scheduler;

import com.notifier.model.Event;
import com.notifier.service.PersonTemplateService;
import com.notifier.web.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Component
public class NotificationScheduler {

    @Autowired
    private PersonTemplateService personTemplateService;

    @Value(value = "${notification.enabled}")
    private Boolean enabled;

    @Scheduled(fixedRateString = "${notification.period}", timeUnit = TimeUnit.SECONDS)
    public void run(){
        if (!enabled)
            return;

        System.out.println("run");
        List<Event> notificationEvents = personTemplateService.findNotificationEvents().stream()
                .peek((e) -> notify(e))
                .toList();
        personTemplateService.saveAllEvents(notificationEvents);
    }

    private void notify(Event event){
        System.out.println("Notification: " + event.getText());
        if (event.getRepeatable()==true)
            event.setNextExecution(LocalDateTime.now().plus(event.getDuration())) ;
        else
            event.setStatus(Status.COMPLETED);

    }

}
