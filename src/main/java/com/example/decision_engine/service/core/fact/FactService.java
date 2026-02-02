package com.example.decision_engine.service.core.fact;

import com.example.decision_engine.entity.facts.Fact;
import com.example.decision_engine.model.fact.FactModel;

public interface FactService {
    Fact createFact(FactModel factModel);
}
