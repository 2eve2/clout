package com.mmm.clout.memberservice.clouter.infrastructure.persistence.jpa;

import com.mmm.clout.memberservice.clouter.domain.Clouter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaClouterRepository extends JpaRepository<Clouter, Long> {
    List<Clouter> findTop10ByOrderByAvgScoreDesc();
}
