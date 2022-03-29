package com.notifier.service;

import com.notifier.web.request.NotifyRq;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationSchedulerService {
    static final String URL_BOT_NOTIFICATION = "http://localhost:8085/notify/event";

    public boolean notifyTelegram(NotifyRq notifyRq) {
        try {
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<NotifyRq> entity = new HttpEntity<>(notifyRq, headers);
            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.exchange(URL_BOT_NOTIFICATION, HttpMethod.POST, entity, NotifyRq.class).getStatusCode() == HttpStatus.OK;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}