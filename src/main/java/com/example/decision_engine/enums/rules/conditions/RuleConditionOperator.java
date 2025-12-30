package com.example.decision_engine.enums.rules.conditions;

import lombok.Getter;

@Getter
public enum RuleConditionOperator {

    EQ("Equals", "="),

    NEQ("Not equals", "!="),

    IN("In", "IN"),

    NOT_IN("Not in", "NOT_IN"),

    GT("Greater than", ">"),

    GTE("Greater than or equals", ">="),

    LT("Less than", "<"),

    LTE("Less than or equals", "<=");

    private final String description;

    private final String symbol;

    RuleConditionOperator(String description, String symbol) {
        this.description = description;
        this.symbol = symbol;
    }
}
