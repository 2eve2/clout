package com.mmm.clout.pointservice.point.infrastructure.config;

import com.mmm.clout.pointservice.point.domain.repository.PointRepository;
import com.mmm.clout.pointservice.point.domain.repository.PointTransactionRepository;
import com.mmm.clout.pointservice.point.infrastructure.PoinRepositoryAdapter;
import com.mmm.clout.pointservice.point.infrastructure.PointTransactionRepositoryAdapter;
import com.mmm.clout.pointservice.point.infrastructure.persistence.jpa.JpaPointRepository;
import com.mmm.clout.pointservice.point.infrastructure.persistence.jpa.JpaPointTransactionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PointRepositoryConfig {

    @Bean
    public PointRepository pointRepository(
        JpaPointRepository jpaPointRepository
    ) {
        return new PoinRepositoryAdapter(jpaPointRepository);
    }

    @Bean
    public PointTransactionRepository pointTransactionRepository(
        JpaPointTransactionRepository jpaPointTransactionRepository
    ) {
        return new PointTransactionRepositoryAdapter(
            jpaPointTransactionRepository
        );
    }

}
