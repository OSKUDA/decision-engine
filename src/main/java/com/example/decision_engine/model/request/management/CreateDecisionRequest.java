package com.example.decision_engine.model.request.management;

import com.example.decision_engine.enums.decisions.DecisionType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateDecisionRequest {

    @Size(max = 100, message = "decisionKey length should be less than 100")
    @NotEmpty(message = "decisionKey is required")
    private String decisionKey;

    @Size(max = 100, message = "name length should be less than 100")
    @NotEmpty(message = "name is required")
    private String name;

    @Size(min = 0, max = 255, message = "description length should be less than 255")
    private String description;

    @NotNull(message = "type is required")
    private DecisionType type;

}
