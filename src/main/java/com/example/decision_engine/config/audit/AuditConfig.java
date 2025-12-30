package com.example.decision_engine.config.audit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@Configuration
public class AuditConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        // TODO : return authentication keys after spring security impl
        return () -> Optional.of("decision-engine");
    }

}
