package com.example.decision_engine.service.management;

import com.example.decision_engine.model.request.management.CreateDecisionRequest;
import com.example.decision_engine.model.response.api.ResponseModel;

public interface ManagementDecisionService {

    ResponseModel createDecision(CreateDecisionRequest request);

}
