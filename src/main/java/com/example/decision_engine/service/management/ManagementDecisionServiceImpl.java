package com.example.decision_engine.service.management;

import com.example.decision_engine.entity.decisions.Decision;
import com.example.decision_engine.model.decisions.DecisionModel;
import com.example.decision_engine.model.request.management.CreateDecisionRequest;
import com.example.decision_engine.model.response.api.ResponseModel;
import com.example.decision_engine.model.response.api.factory.ResponseModelFactory;
import com.example.decision_engine.model.response.decisions.DecisionResponse;
import com.example.decision_engine.model.response.management.PagedDecisionResponse;
import com.example.decision_engine.service.core.decision.DecisionService;
import com.example.decision_engine.util.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@Transactional
public class ManagementDecisionServiceImpl implements ManagementDecisionService {

    private final DecisionService decisionService;

    public ManagementDecisionServiceImpl(DecisionService decisionService) {
        this.decisionService = decisionService;
    }

    @Override
    public ResponseModel createDecision(CreateDecisionRequest request) {
        Decision decision = decisionService.createDecision(DecisionModel.builder()
                .decisionKey(request.getDecisionKey())
                .name(request.getName())
                .type(request.getType())
                .description(request.getDescription())
                .isActive(Boolean.TRUE)
                .build());

        // build response
        return ResponseModelFactory.success(
                ResponseMessage.Management.SUCCESS_CREATED_DECISION,
                DecisionResponse.builder()
                        .decisionKey(decision.getDecisionKey())
                        .build(),
                HttpStatus.CREATED
        );
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseModel fetchPaginatedDecisions(int page, int size) {
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(Sort.Direction.ASC, "createdAt")
        );
        Page<Decision> pageResult = decisionService.fetchAllPaginatedDecision(pageable);

        // build response
        Page<DecisionResponse> decisionResponses = pageResult.map(
                decision -> DecisionResponse.builder()
                        .decisionKey(decision.getDecisionKey())
                        .name(decision.getName())
                        .description(decision.getDescription())
                        .decisionType(decision.getType())
                        .isActive(decision.getIsActive())
                        .build()
        );

        return ResponseModelFactory.success(
                ResponseMessage.Management.SUCCESS_FETCHED_DECISIONS,
                PagedDecisionResponse.builder()
                        .decisions(decisionResponses.getContent())
                        .page(pageResult.getNumber())
                        .size(pageResult.getSize())
                        .totalElements(pageResult.getTotalElements())
                        .totalPages(pageResult.getTotalPages())
                        .hasNext(pageResult.hasNext())
                        .hasPrevious(pageResult.hasPrevious())
                        .build(),
                HttpStatus.OK
        );
    }
}
