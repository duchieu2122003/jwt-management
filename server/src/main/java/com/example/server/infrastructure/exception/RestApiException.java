package com.example.server.infrastructure.exception;

import com.example.server.infrastructure.constant.Message;
import lombok.Getter;
import lombok.Setter;

/**
 * @author duchieu212
 */
@Getter
@Setter
public class RestApiException extends RuntimeException {

    private String message;

    public RestApiException() {
    }

    public RestApiException(Message statusCode) {
        this.message = statusCode.getMessage();
    }

    public RestApiException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
