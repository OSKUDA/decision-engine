package com.example.decision_engine.mapper.decisions;

import com.example.decision_engine.entity.decisions.Decision;
import com.example.decision_engine.model.decisions.DecisionModel;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class DecisionMapper {

    public Decision toEntity(DecisionModel model) {
        return Decision.builder()
                .decisionKey(model.getDecisionKey())
                .name(model.getName())
                .description(model.getDescription())
                .type(model.getType())
                .isActive(model.getIsActive())
                .build();
    }

    public void updateEntity(Decision decision, DecisionModel model) {
        decision.setDecisionKey(model.getDecisionKey());
        decision.setName(model.getName());
        decision.setDescription(model.getDescription());
        decision.setType(decision.getType());
        decision.setIsActive(decision.getIsActive());
    }

    public void patchEntity(Decision decision, DecisionModel model) {
        decision.setDecisionKey(Objects.nonNull(model.getDecisionKey()) ? model.getDecisionKey() : decision.getDecisionKey());
        decision.setName(Objects.nonNull(model.getName()) ? model.getName() : decision.getName());
        decision.setDescription(Objects.nonNull(model.getDescription()) ? model.getDescription() : decision.getDescription());
        decision.setType(Objects.nonNull(model.getType()) ? model.getType() : decision.getType());
        decision.setIsActive(Objects.nonNull(model.getIsActive()) ? model.getIsActive() : decision.getIsActive());
    }

}
