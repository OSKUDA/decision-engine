package com.example.decision_engine.service.core.fact;

import com.example.decision_engine.entity.facts.Fact;
import com.example.decision_engine.exception.api.ResourceAlreadyExistsException;
import com.example.decision_engine.mapper.fact.FactMapper;
import com.example.decision_engine.model.fact.FactModel;
import com.example.decision_engine.repository.facts.FactRepository;
import com.example.decision_engine.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FactServiceImpl implements FactService {

    private final FactRepository factRepository;

    private final FactMapper factMapper;

    public FactServiceImpl(FactRepository factRepository, FactMapper factMapper) {
        this.factRepository = factRepository;
        this.factMapper = factMapper;
    }

    @Override
    public Fact createFact(FactModel factModel) {
        if (factRepository.existsByFactKey(factModel.getFactKey())) {
            log.warn("FAILED : Fact already exists | fact_key : {}", factModel.getFactKey());
            throw new ResourceAlreadyExistsException(Constant.Entity.Fact.NAME, Constant.Entity.Fact.FACT_KEY, factModel.getFactKey());
        }

        Fact fact = factRepository.save(factMapper.toEntity(factModel));
        log.info("SUCCESS : Fact created successfully | fact_id : {} | fact_key : {}", fact.getId(), fact.getFactKey());

        return fact;
    }
}
