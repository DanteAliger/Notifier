package com.notifier.web.response;

import com.notifier.model.Person;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PersonResponse {

    private final Long IdTelegram;
    private final String name;


    public static PersonResponse getIdTelegramResponse(Person request){
        PersonResponse personResponse = new PersonResponse(request.getIdTelegram(), request.getName());
        return personResponse;
    }
}
