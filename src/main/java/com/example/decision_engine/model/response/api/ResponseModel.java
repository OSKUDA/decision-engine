package com.example.decision_engine.model.response.api;

import com.example.decision_engine.enums.errors.ErrorCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ResponseModel {

    private Instant timestamp;

    private String path;

    private HttpStatus status;

    private ErrorCode code;

    private String message;

    private String traceId;

    private List<FieldError> details;

    private Object errors;

    private Object data;

}
