package com.mmm.clout.memberservice.advertiser.infrastructure.persistence;

import com.mmm.clout.memberservice.advertiser.domain.Advertiser;
import com.mmm.clout.memberservice.advertiser.domain.repository.AdvertiserRepository;
import com.mmm.clout.memberservice.advertiser.domain.exception.NotFoundAdvertiser;
import com.mmm.clout.memberservice.advertiser.infrastructure.persistence.jpa.JpaAdvertiserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AdvertiserRepositoryAdapter implements AdvertiserRepository {

    private final JpaAdvertiserRepository jpaAdvertisementRepository;

    @Override
    public Advertiser save(Advertiser advertiser) {
        return jpaAdvertisementRepository.save(advertiser);
    }

    @Override
    public Advertiser findById(Long userId) {
        return jpaAdvertisementRepository.findById(userId).orElseThrow(
            () -> new NotFoundAdvertiser()
        );
    }
}
