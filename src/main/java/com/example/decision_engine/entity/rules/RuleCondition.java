package com.example.decision_engine.entity.rules;


import com.example.decision_engine.entity.Audit;
import com.example.decision_engine.entity.facts.Fact;
import com.example.decision_engine.enums.rules.conditions.RuleConditionOperator;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@Table(name = "rule_conditions", schema = "decision_engine")
public class RuleCondition extends Audit {

    @Id
    @ToString.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rule_con_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rule_id", nullable = false)
    private Rule rule;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fact_id", nullable = false)
    private Fact fact;

    @ToString.Include
    @Enumerated(EnumType.STRING)
    @Column(name = "operator", nullable = false)
    private RuleConditionOperator operator;

    @ToString.Include
    @Column(name = "expected_value", length = 255, nullable = false)
    private String expectedValue;

    @ToString.Include
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

}
