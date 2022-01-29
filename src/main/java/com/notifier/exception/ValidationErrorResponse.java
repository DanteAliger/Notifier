package com.notifier.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ValidationErrorResponse{
    private final List<Error> errors;

    @Getter
    @RequiredArgsConstructor
    public static class Error{
        private final String fieldName;
        private final String message;
    }

}
