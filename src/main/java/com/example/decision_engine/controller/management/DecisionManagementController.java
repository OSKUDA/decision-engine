package com.example.decision_engine.controller.management;

import com.example.decision_engine.model.request.management.CreateDecisionRequest;
import com.example.decision_engine.model.response.api.ResponseModel;
import com.example.decision_engine.service.management.ManagementService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/internal/decisions")
public class DecisionManagementController {

    private final ManagementService managementService;

    public DecisionManagementController(ManagementService managementService) {
        this.managementService = managementService;
    }

    @PostMapping("")
    public ResponseEntity<ResponseModel> createDecision(@Valid @RequestBody CreateDecisionRequest request){
        ResponseModel responseModel = managementService.createDecision(request);
        return new ResponseEntity<>(responseModel, responseModel.getStatus());
    }

    @GetMapping("")
    public ResponseEntity<ResponseModel> fetchDecisions(@RequestParam("page") int page, @RequestParam("size") int size) {
        ResponseModel responseModel = managementService.fetchPaginatedDecisions(page, size);
        return new ResponseEntity<>(responseModel, responseModel.getStatus());
    }

}
