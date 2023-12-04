package com.mmm.clout.memberservice.advertiser.infrastructure.persistence.jpa;

import com.mmm.clout.memberservice.advertiser.domain.Advertiser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAdvertiserRepository extends JpaRepository<Advertiser, Long> {
}
