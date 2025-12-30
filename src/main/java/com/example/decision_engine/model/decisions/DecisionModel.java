package com.example.decision_engine.model.decisions;

import com.example.decision_engine.enums.decisions.DecisionType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class DecisionModel {

    private String decisionKey;

    private String name;

    private String description;

    private DecisionType type;

    private Boolean isActive;

}
