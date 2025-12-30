package com.example.decision_engine.repository.decisions;

import com.example.decision_engine.entity.decisions.Decision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DecisionRepository extends JpaRepository<Decision, Long> {

    boolean existsByDecisionKey(String decisionKey);

    Optional<Decision> findByDecisionKey(String decisionKey);

    Optional<Decision> findByDecisionKeyAndIsActive(String decisionKey, Boolean isActive);

}
