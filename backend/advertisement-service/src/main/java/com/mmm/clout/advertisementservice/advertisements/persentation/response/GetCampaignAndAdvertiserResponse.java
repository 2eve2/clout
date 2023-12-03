package com.mmm.clout.advertisementservice.advertisements.persentation.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.mmm.clout.advertisementservice.advertisements.application.reader.CampaignReader;
import com.mmm.clout.advertisementservice.advertisements.application.reader.CampaignReaderWithAdvertiser;
import com.mmm.clout.advertisementservice.advertisements.domain.Campaign;
import com.mmm.clout.advertisementservice.common.msa.info.AdvertiserInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonInclude(Include.NON_NULL)
@Getter
@AllArgsConstructor
public class GetCampaignAndAdvertiserResponse {

    private CampaignResponse campaignInfo;

    private AdvertiserResponse advertiserInfo;

    public static GetCampaignAndAdvertiserResponse from(CampaignReaderWithAdvertiser reader) {
        CampaignReader campaign = reader.getCampaign();
        AdvertiserInfo advertiser = reader.getAdvertiserInfo();

        return new GetCampaignAndAdvertiserResponse(
            CampaignResponse.from(campaign),
            AdvertiserResponse.from(advertiser)
        );
    }
}

