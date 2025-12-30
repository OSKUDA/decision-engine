package com.example.decision_engine.entity.rules;

import com.example.decision_engine.entity.Audit;
import com.example.decision_engine.entity.decisions.Decision;
import com.example.decision_engine.entity.outcomes.Outcome;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@Table(name = "rules", schema = "decision_engine")
public class Rule extends Audit {

    @Id
    @ToString.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rule_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "decision_id", nullable = false)
    private Decision decision;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "outcome_id", nullable = false)
    private Outcome outcome;

    @ToString.Include
    @Column(name = "priority", nullable = false)
    private Integer priority;

    @ToString.Include
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

}
