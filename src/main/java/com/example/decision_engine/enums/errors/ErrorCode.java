package com.example.decision_engine.enums.errors;

import lombok.Getter;

@Getter
public enum ErrorCode {

    E_V_001("Validation error"),

    E_R_001("Resource not found"),

    E_R_002("Resource already exists"),

    E_U_001("Unexpected error");

    private final String description;

    ErrorCode(String description) {
        this.description = description;
    }

}
