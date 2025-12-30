package com.example.decision_engine.repository.rules;

import com.example.decision_engine.entity.rules.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleRepository extends JpaRepository<Rule, Long> {
}
