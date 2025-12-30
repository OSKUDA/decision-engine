package com.example.decision_engine.enums.decisions;

import lombok.Getter;

@Getter
public enum DecisionType {

    GENERIC("Generic"),

    EXPERIMENT_VARIANT("A/B or multivariate experiments"),

    FEATURE_TOGGLE("Enable/Disable features");

    private final String description;

    DecisionType(String description) {
        this.description = description;
    }

}
