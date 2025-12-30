package com.example.decision_engine.exception.handler;

import com.example.decision_engine.enums.errors.ErrorCode;
import com.example.decision_engine.exception.api.ApiException;
import com.example.decision_engine.model.response.api.FieldError;
import com.example.decision_engine.model.response.api.ResponseModel;
import com.example.decision_engine.model.response.api.factory.ResponseModelFactory;
import com.example.decision_engine.util.ResponseMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseModel> handleValidation(
            MethodArgumentNotValidException e,
            HttpServletRequest request) {

        List<FieldError> fieldErrors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> new FieldError(err.getField(), err.getDefaultMessage()))
                .toList();

        ResponseModel response = ResponseModelFactory.error(
                ResponseMessage.GlobalException.PARAMETER_MISMATCHED,
                ErrorCode.E_V_001,
                e.getMessage(),
                Objects.nonNull(request) ? request.getRequestURI() : null,
                fieldErrors,
                getTraceId(),
                HttpStatus.NOT_ACCEPTABLE
        );
        return new ResponseEntity<>(response,response.getStatus());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseModel> handleConstraintViolation(
            ConstraintViolationException e,
            HttpServletRequest request) {
        List<FieldError> errors = e.getConstraintViolations()
                .stream()
                .map(cv -> new FieldError(cv.getPropertyPath().toString(), cv.getMessage()))
                .collect(Collectors.toList());

        ResponseModel response = ResponseModelFactory.error(
                ResponseMessage.GlobalException.PARAMETER_MISMATCHED,
                ErrorCode.E_V_001,
                e.getMessage(),
                Objects.nonNull(request) ? request.getRequestURI() : null,
                errors,
                getTraceId(),
                HttpStatus.NOT_ACCEPTABLE
        );

        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ResponseModel> handleMissingParams(
            MissingServletRequestParameterException e,
            HttpServletRequest request) {

        FieldError error = new FieldError(
                e.getParameterName(),
                "parameter is required"
        );

        ResponseModel response = ResponseModelFactory.error(
                ResponseMessage.GlobalException.PARAMETER_MISMATCHED,
                ErrorCode.E_V_001,
                e.getMessage(),
                request.getRequestURI(),
                List.of(error),
                getTraceId(),
                HttpStatus.BAD_REQUEST
        );

        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ResponseModel> handleApiException(ApiException e, HttpServletRequest request) {
        ResponseModel response = ResponseModelFactory.error(
                e.getGenericMessage(),
                e.getErrorCode(),
                e.getMessage(),
                Objects.nonNull(request) ? request.getRequestURI() : null,
                null,
                getTraceId(),
                e.getHttpStatus()
        );
        return new ResponseEntity<>(response,response.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseModel> handleException(Exception e, HttpServletRequest request) {
        ResponseModel response = ResponseModelFactory.error(
                ResponseMessage.GlobalException.UNEXPECTED_ERROR,
                ErrorCode.E_U_001,
                e.getMessage(),
                Objects.nonNull(request) ? request.getRequestURI() : null,
                null,
                getTraceId(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
        return new ResponseEntity<>(response,response.getStatus());
    }

    private String getTraceId() {
        return MDC.get("traceId");
    }

}
