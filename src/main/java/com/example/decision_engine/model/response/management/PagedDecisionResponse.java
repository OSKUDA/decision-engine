package com.example.decision_engine.model.response.management;

import com.example.decision_engine.model.response.decisions.DecisionResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PagedDecisionResponse {


    private List<DecisionResponse> decisions;

    private int page;
    private int size;

    private long totalElements;
    private int totalPages;

    private boolean hasNext;
    private boolean hasPrevious;

}
