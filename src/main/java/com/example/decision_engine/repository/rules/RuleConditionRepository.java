package com.example.decision_engine.repository.rules;

import com.example.decision_engine.entity.rules.RuleCondition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleConditionRepository extends JpaRepository<RuleCondition, Long> {
}
