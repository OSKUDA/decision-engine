package com.example.decision_engine.exception.api;

import com.example.decision_engine.enums.errors.ErrorCode;
import com.example.decision_engine.util.ResponseMessage;
import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ApiException {

    public ResourceNotFoundException(String message) {
        super(ResponseMessage.GlobalException.RESOURCE_NOT_FOUND, message, ErrorCode.E_R_001, HttpStatus.NOT_FOUND);
    }

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(ResponseMessage.GlobalException.RESOURCE_NOT_FOUND, String.format("%s resource not available %s : '%s'", resourceName, fieldName, fieldValue), ErrorCode.E_R_001, HttpStatus.NOT_FOUND);
    }
}
