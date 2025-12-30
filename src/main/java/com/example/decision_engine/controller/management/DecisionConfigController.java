package com.example.decision_engine.controller.management;

import com.example.decision_engine.model.request.management.CreateDecisionRequest;
import com.example.decision_engine.model.response.api.ResponseModel;
import com.example.decision_engine.service.management.ManagementDecisionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/internal/decisions")
public class DecisionConfigController {

    private final ManagementDecisionService managementDecisionService;

    public DecisionConfigController(ManagementDecisionService managementDecisionService) {
        this.managementDecisionService = managementDecisionService;
    }

    @PostMapping("")
    public ResponseEntity<ResponseModel> createDecision(@Valid @RequestBody CreateDecisionRequest request){
        ResponseModel responseModel = managementDecisionService.createDecision(request);
        return new ResponseEntity<>(responseModel, responseModel.getStatus());
    }

    @GetMapping("")
    public ResponseEntity<ResponseModel> fetchDecisions(@RequestParam("page") int page, @RequestParam("size") int size) {
        ResponseModel responseModel = managementDecisionService.fetchPaginatedDecisions(page, size);
        return new ResponseEntity<>(responseModel, responseModel.getStatus());
    }

}
