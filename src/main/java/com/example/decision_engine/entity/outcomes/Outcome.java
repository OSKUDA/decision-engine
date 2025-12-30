package com.example.decision_engine.entity.outcomes;

import com.example.decision_engine.entity.Audit;
import com.example.decision_engine.enums.outcomes.OutcomeType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@Table(name = "outcomes", schema = "decision_engine")
public class Outcome extends Audit {

    @Id
    @ToString.Include
    @Column(name = "outcome_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Include
    @Column(name = "outcome_key", nullable = false, unique = true, length = 100)
    private String outcomeKey;

    @ToString.Include
    @Column(name = "name", length = 100)
    private String name;

    @ToString.Include
    @Enumerated(EnumType.STRING)
    @Column(name = "type", length = 50, nullable = false)
    private OutcomeType type;

    @Column(name = "payload", nullable = false, columnDefinition = "json")
    private String payload;

    @ToString.Include
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

}
