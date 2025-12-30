package com.example.decision_engine.model.response.management;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class CreateDecisionResponse {

    private String decisionKey;

}
