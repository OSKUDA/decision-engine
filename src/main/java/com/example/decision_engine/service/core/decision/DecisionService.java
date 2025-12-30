package com.example.decision_engine.service.core.decision;

import com.example.decision_engine.entity.decisions.Decision;
import com.example.decision_engine.model.decisions.DecisionModel;

import java.util.Optional;

public interface DecisionService {
    Decision createDecision(DecisionModel decisionModel);

    Decision updateDecision(DecisionModel decisionModel);

    Decision getDecisionByDecisionKey(String decisionKey);

    Optional<Decision> getOptDecisionByDecisionKey(String decisionKey);

    Decision getActiveDecisionByDecisionKey(String decisionKey);

    Optional<Decision> getOptActiveDecisionByDecisionKey(String decisionKey);
}
