package com.example.decision_engine.controller.management;

import com.example.decision_engine.model.request.management.CreateFactRequest;
import com.example.decision_engine.model.response.api.ResponseModel;
import com.example.decision_engine.service.management.ManagementService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal/facts")
public class FactManagementController {

    private final ManagementService managementService;

    public FactManagementController(ManagementService managementService) {
        this.managementService = managementService;
    }

    @PostMapping("")
    public ResponseEntity<ResponseModel> addFact(@Valid @RequestBody CreateFactRequest createFactRequest) {
        ResponseModel responseModel = managementService.createFact(createFactRequest);
        return new ResponseEntity<>(responseModel, responseModel.getStatus());
    }

}
