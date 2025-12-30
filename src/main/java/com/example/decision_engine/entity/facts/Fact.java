package com.example.decision_engine.entity.facts;

import com.example.decision_engine.entity.Audit;
import com.example.decision_engine.enums.facts.FactDataType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@Table(name = "facts", schema = "decision_engine")
public class Fact extends Audit {

    @Id
    @ToString.Include
    @Column(name = "fact_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Include
    @Column(name = "fact_key", length = 100, unique = true, nullable = false)
    private String factKey;

    @ToString.Include
    @Column(name = "name", length = 100)
    private String name;

    @ToString.Include
    @Enumerated(EnumType.STRING)
    @Column(name = "data_type", nullable = false)
    private FactDataType dataType;

    @ToString.Include
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

}
