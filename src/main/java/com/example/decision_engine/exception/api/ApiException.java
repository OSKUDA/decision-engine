package com.example.decision_engine.exception.api;

import com.example.decision_engine.enums.errors.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class ApiException extends RuntimeException {

    private final ErrorCode errorCode;

    private final HttpStatus httpStatus;

    private final String genericMessage;

    public ApiException(String genericMessage, String message, ErrorCode errorCode, HttpStatus httpStatus) {
        super(message);
        this.genericMessage = genericMessage;
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

}
