package com.example.decision_engine.mapper.fact;

import com.example.decision_engine.entity.facts.Fact;
import com.example.decision_engine.model.fact.FactModel;
import org.springframework.stereotype.Component;

@Component
public class FactMapper {

    public Fact toEntity(FactModel model) {
        return Fact.builder()
                .factKey(model.getFactKey())
                .name(model.getName())
                .dataType(model.getDataType())
                .isActive(model.getIsActive())
                .build();
    }
}
