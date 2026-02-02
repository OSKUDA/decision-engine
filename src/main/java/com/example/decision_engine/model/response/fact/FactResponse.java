package com.example.decision_engine.model.response.fact;

import com.example.decision_engine.enums.facts.FactDataType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class FactResponse {

    private String factKey;

    private String name;

    private FactDataType dataType;

    private Boolean isActive;

}
