package com.mmm.clout.memberservice.advertiser.domain.repository;

import com.mmm.clout.memberservice.advertiser.domain.Advertiser;

public interface AdvertiserRepository {

    Advertiser save(Advertiser advertiser);

    Advertiser findById(Long userId);
}
