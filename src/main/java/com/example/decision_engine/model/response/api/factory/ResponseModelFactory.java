package com.example.decision_engine.model.response.api.factory;

import com.example.decision_engine.enums.errors.ErrorCode;
import com.example.decision_engine.model.response.api.FieldError;
import com.example.decision_engine.model.response.api.ResponseModel;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.List;

public class ResponseModelFactory {

    private ResponseModelFactory() {
    }

    public static ResponseModel success(String message, Object data, HttpStatus httpStatus) {
        return ResponseModel.builder()
                .timestamp(Instant.now())
                .status(httpStatus)
                .message(message)
                .data(data)
                .build();
    }

    public static ResponseModel error(String message, ErrorCode errorCode, Object errors, String requestURI, List<FieldError> fieldErrors, String traceId, HttpStatus httpStatus) {
        return ResponseModel.builder()
                .timestamp(Instant.now())
                .path(requestURI)
                .status(httpStatus)
                .message(message)
                .traceId(traceId)
                .code(errorCode)
                .details(fieldErrors)
                .errors(errors)
                .build();
    }
}
