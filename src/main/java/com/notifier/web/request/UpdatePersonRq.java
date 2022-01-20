package com.notifier.web.request;

import lombok.Data;

@Data
public class UpdatePersonRq  {
    private String name;
    private String surname;
    private String phone;
    private String email;
}
