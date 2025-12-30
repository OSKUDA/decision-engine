package com.example.decision_engine.entity.decisions;

import com.example.decision_engine.entity.Audit;
import com.example.decision_engine.enums.decisions.DecisionType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@Table(name = "decisions", schema = "decision_engine")
public class Decision extends Audit {

    @Id
    @ToString.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "decision_id", updatable = false)
    private Long id;

    @ToString.Include
    @Column(name = "decision_key", unique = true)
    private String decisionKey;

    @ToString.Include
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @ToString.Include
    @Column(name = "description", length = 255)
    private String description;

    @ToString.Include
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private DecisionType type;

    @ToString.Include
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

}
