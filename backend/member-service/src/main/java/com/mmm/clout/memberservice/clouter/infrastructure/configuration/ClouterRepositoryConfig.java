package com.mmm.clout.memberservice.clouter.infrastructure.configuration;

import com.mmm.clout.memberservice.clouter.domain.repository.ClouterRepository;
import com.mmm.clout.memberservice.clouter.infrastructure.persistence.ClouterRepositoryAdapter;
import com.mmm.clout.memberservice.clouter.infrastructure.persistence.jpa.JpaClouterRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClouterRepositoryConfig {

    @Bean
    public ClouterRepository clouterRepository(JpaClouterRepository jpaClouterRepository) {
        return new ClouterRepositoryAdapter(jpaClouterRepository);
    }
}
