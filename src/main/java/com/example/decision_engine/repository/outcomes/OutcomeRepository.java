package com.example.decision_engine.repository.outcomes;

import com.example.decision_engine.entity.outcomes.Outcome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutcomeRepository extends JpaRepository<Outcome, Long> {
}
