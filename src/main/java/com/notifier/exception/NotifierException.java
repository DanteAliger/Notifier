package com.notifier.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter //  создает гетеры
@RequiredArgsConstructor // конструктор с обяхательными полями
public class NotifierException extends Exception {
    private final ErrorCode code;
    private final HttpStatus status;
}
