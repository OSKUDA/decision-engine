package com.example.decision_engine.repository.facts;

import com.example.decision_engine.entity.facts.Fact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactRepository extends JpaRepository<Fact, Long> {
}
