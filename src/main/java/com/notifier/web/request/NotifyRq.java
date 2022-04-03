package com.notifier.web.request;

import lombok.Data;

@Data
public class NotifyRq {
    private final Long idTelegram;
    private final String textEvent;

}
