package com.notifier.exception;

import lombok.Data;

@Data
public class ErrorResponse {

    private final Error error;

    public ErrorResponse(ErrorCode code){
        this.error = new Error(code);
    }

    @Data
    public static class Error{
        private final ErrorCode code;
    }
}
