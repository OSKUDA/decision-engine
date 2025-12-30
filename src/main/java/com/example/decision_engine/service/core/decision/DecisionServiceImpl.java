package com.example.decision_engine.service.core.decision;

import com.example.decision_engine.entity.decisions.Decision;
import com.example.decision_engine.exception.api.ResourceAlreadyExistsException;
import com.example.decision_engine.exception.api.ResourceNotFoundException;
import com.example.decision_engine.mapper.decisions.DecisionMapper;
import com.example.decision_engine.model.decisions.DecisionModel;
import com.example.decision_engine.repository.decisions.DecisionRepository;
import com.example.decision_engine.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class DecisionServiceImpl implements DecisionService {

    private final DecisionRepository decisionRepository;

    private final DecisionMapper decisionMapper;


    public DecisionServiceImpl(DecisionRepository decisionRepository, DecisionMapper decisionMapper) {
        this.decisionRepository = decisionRepository;
        this.decisionMapper = decisionMapper;
    }

    @Override
    public Decision createDecision(DecisionModel decisionModel) {
        if (decisionRepository.existsByDecisionKey(decisionModel.getDecisionKey())) {
            log.warn("FAILED : Decision already exists | decision_key : {}", decisionModel.getDecisionKey());
            throw new ResourceAlreadyExistsException(Constant.Entity.Decision.NAME, Constant.Entity.Decision.DECISION_KEY, decisionModel.getDecisionKey());
        }

        Decision decision = decisionRepository.save(decisionMapper.toEntity(decisionModel));
        log.info("SUCCESS : Decision created successfully | decision_id : {} | decision_key : {}", decision.getId(), decision.getDecisionKey());

        return decision;
    }

    @Override
    public Decision updateDecision(DecisionModel decisionModel) {
        Decision existing = decisionRepository.findByDecisionKey(decisionModel.getDecisionKey())
                .orElseThrow(() -> new ResourceNotFoundException(Constant.Entity.Decision.NAME, Constant.Entity.Decision.DECISION_KEY, decisionModel.getDecisionKey()));

        decisionMapper.updateEntity(existing, decisionModel);

        Decision saved = decisionRepository.save(existing);
        log.info("SUCCESS : Decision updated successfully | decision_id : {} | decision_key : {}", saved.getId(), saved.getDecisionKey());

        return saved;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Decision> fetchAllDecision() {
        List<Decision> decisions = decisionRepository.findAll();

        log.info("SUCCESS : Fetched decisions | size : {}", decisions.size());
        return decisions;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Decision> fetchAllActiveDecision() {
        List<Decision> decisions = decisionRepository.findAllByIsActive(Boolean.TRUE);
        log.info("SUCCESS : Fetched active decisions | size : {}", decisions.size());
        return decisions;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Decision> fetchAllPaginatedDecision(Pageable pageable) {

        Page<Decision> page = decisionRepository.findAll(pageable);

        log.info(
                "Fetched paginated decisions | page={} | size={} | totalElements={} | returned={}",
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getNumberOfElements()
        );

        return page;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Decision> fetchAllPaginatedActiveDecision(Pageable pageable) {

        Page<Decision> page = decisionRepository.findAllByIsActive(Boolean.TRUE, pageable);

        log.info(
                "Fetched paginated active decisions | page={} | size={} | totalElements={} | returned={}",
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getNumberOfElements()
        );

        return page;
    }

    @Override
    @Transactional(readOnly = true)
    public Decision getDecisionByDecisionKey(String decisionKey) {
        Decision decision = decisionRepository.findByDecisionKey(decisionKey)
                .orElseThrow((() -> new ResourceNotFoundException(Constant.Entity.Decision.NAME, Constant.Entity.Decision.DECISION_KEY, decisionKey)));

        log.info("SUCCESS : Fetched Decision | decision_id : {} | decision_key : {}", decision.getId(), decision.getDecisionKey());
        return decision;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Decision> getOptDecisionByDecisionKey(String decisionKey) {
        Optional<Decision> decisionOptional = decisionRepository.findByDecisionKey(decisionKey);

        decisionOptional.ifPresentOrElse(
                (decision) -> log.info("SUCCESS : Found Decision | decision_id : {} | decision_key : {}", decision.getId(), decision.getDecisionKey()),
                () -> log.info("FAILED : Decision not found for decision_key : {}",decisionKey)
        );

        return decisionOptional;
    }

    @Override
    @Transactional(readOnly = true)
    public Decision getActiveDecisionByDecisionKey(String decisionKey) {
        Decision decision = decisionRepository.findByDecisionKeyAndIsActive(decisionKey, Boolean.TRUE)
                .orElseThrow((() -> new ResourceNotFoundException(Constant.Entity.Decision.NAME, Constant.Entity.Decision.DECISION_KEY, decisionKey)));

        log.info("SUCCESS : Found active Decision | decision_id : {} | decision_key : {}", decision.getId(), decision.getDecisionKey());
        return decision;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Decision> getOptActiveDecisionByDecisionKey(String decisionKey) {
        Optional<Decision> decisionOptional = decisionRepository.findByDecisionKeyAndIsActive(decisionKey, Boolean.TRUE);

        decisionOptional.ifPresentOrElse(
                (decision) -> log.info("SUCCESS : Found active Decision | decision_id : {} | decision_key : {}", decision.getId(), decision.getDecisionKey()),
                () -> log.info("FAILED : Decision not found for decision_key : {}", decisionKey)
        );

        return decisionOptional;
    }



}
