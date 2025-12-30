package com.example.decision_engine.exception.api;

import com.example.decision_engine.enums.errors.ErrorCode;
import com.example.decision_engine.util.ResponseMessage;
import org.springframework.http.HttpStatus;

public class ResourceAlreadyExistsException extends ApiException {
    public ResourceAlreadyExistsException(String message) {
        super(ResponseMessage.GlobalException.RESOURCE_ALREADY_EXISTS, message, ErrorCode.E_R_002, HttpStatus.CONFLICT);
    }

    public ResourceAlreadyExistsException(String resourceName, String fieldName, Object fieldValue) {
        super(ResponseMessage.GlobalException.RESOURCE_ALREADY_EXISTS, String.format("%s resource already available %s : '%s'", resourceName, fieldName, fieldValue), ErrorCode.E_R_002, HttpStatus.CONFLICT);
    }

}
