package com.example.decision_engine.service.core.decision;

import com.example.decision_engine.entity.decisions.Decision;
import com.example.decision_engine.model.decisions.DecisionModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface DecisionService {
    Decision createDecision(DecisionModel decisionModel);

    Decision updateDecision(DecisionModel decisionModel);

    List<Decision> fetchAllDecision();

    List<Decision> fetchAllActiveDecision();

    @Transactional(readOnly = true)
    Page<Decision> fetchAllPaginatedDecision(Pageable pageable);

    @Transactional(readOnly = true)
    Page<Decision> fetchAllPaginatedActiveDecision(Pageable pageable);

    Decision getDecisionByDecisionKey(String decisionKey);

    Optional<Decision> getOptDecisionByDecisionKey(String decisionKey);

    Decision getActiveDecisionByDecisionKey(String decisionKey);

    Optional<Decision> getOptActiveDecisionByDecisionKey(String decisionKey);
}
