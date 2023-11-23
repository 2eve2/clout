package com.mmm.clout.advertisementservice.advertisements.application;

import com.mmm.clout.advertisementservice.advertisements.application.reader.CampaignReader;
import com.mmm.clout.advertisementservice.advertisements.application.reader.CampaignReaderWithAdvertiser;
import com.mmm.clout.advertisementservice.advertisements.domain.Campaign;
import com.mmm.clout.advertisementservice.advertisements.domain.repository.CampaignRepository;
import com.mmm.clout.advertisementservice.common.msa.provider.MemberProvider;
import java.util.ArrayList;
import java.util.List;

import com.mmm.clout.advertisementservice.image.domain.AdvertiseSign;
import com.mmm.clout.advertisementservice.image.domain.Image;
import com.mmm.clout.advertisementservice.image.domain.repository.AdvertiseSignRepository;
import com.mmm.clout.advertisementservice.image.domain.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class GetTop10CampaignListProcessor {

    private final CampaignRepository campaignRepository;
    private final MemberProvider memberProvider;
    private final ImageRepository imageRepository;
    private final AdvertiseSignRepository advertiseSignRepository;

    @Transactional
    public List<CampaignReaderWithAdvertiser> execute() {
        List<Campaign> top10List = campaignRepository.getTop10List();

        // TODO n+1 성능 최적화 필요
        List<CampaignReaderWithAdvertiser> content = new ArrayList<>();
        for (Campaign campaign : top10List) {
            List<Image> images = imageRepository.findByCampaignId(campaign.getId());
            AdvertiseSign sign = advertiseSignRepository.findByAdvertisementId(campaign.getId());
            content.add(
                new CampaignReaderWithAdvertiser(
                    new CampaignReader(campaign),
                    memberProvider.getAdvertiserInfoByMemberId(campaign.getAdvertiserId())
                )
            );
        }

        return content;
    }
}
