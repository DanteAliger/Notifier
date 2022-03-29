package com.notifier.web.response;

import com.notifier.web.utils.Status;
import lombok.Getter;

@Getter
public class TemplateResponse {
    private Long id;
    private String nameTemplate;
    private Status status;

    public TemplateResponse(Long id, String nameTemplate, Status status) {
        this.id=id;
        this.nameTemplate = nameTemplate;
        this.status = status;
    }
}
