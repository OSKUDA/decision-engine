package com.example.decision_engine.model.request.management;

import com.example.decision_engine.enums.decisions.DecisionType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateDecisionRequest {

    @NotEmpty(message = "decisionKey is required")
    private String decisionKey;

    @NotEmpty(message = "name is required")
    private String name;

    private String description;

    @NotNull(message = "type is required")
    private DecisionType type;

}
