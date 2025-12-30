package com.example.decision_engine.enums.facts;

import lombok.Getter;

@Getter
public enum FactDataType {

    BOOLEAN("boolean"),

    STRING("string"),

    NUMBER("number"),

    DATE("date");

    private final String description;

    FactDataType(String description) {
        this.description = description;
    }
}
