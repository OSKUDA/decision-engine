package com.example.decision_engine.repository.decisions;

import com.example.decision_engine.entity.decisions.Decision;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DecisionRepository extends JpaRepository<Decision, Long> {

    boolean existsByDecisionKey(String decisionKey);

    List<Decision> findAllByIsActive(Boolean isActive);

    Page<Decision> findAllByIsActive(Boolean isActive, Pageable pageable);

    Optional<Decision> findByDecisionKey(String decisionKey);

    Optional<Decision> findByDecisionKeyAndIsActive(String decisionKey, Boolean isActive);

}
