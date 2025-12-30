package com.example.decision_engine.model.response.decisions;

import com.example.decision_engine.enums.decisions.DecisionType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class DecisionResponse {

    private String decisionKey;

    private String name;

    private String description;

    private DecisionType decisionType;

    private Boolean isActive;

}
