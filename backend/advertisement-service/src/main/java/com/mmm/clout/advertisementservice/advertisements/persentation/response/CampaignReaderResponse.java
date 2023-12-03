package com.mmm.clout.advertisementservice.advertisements.persentation.response;

import com.mmm.clout.advertisementservice.advertisements.application.reader.CampaignListReader;
import com.mmm.clout.advertisementservice.advertisements.application.reader.CampaignReader;
import com.mmm.clout.advertisementservice.advertisements.application.reader.CampaignReaderWithAdvertiser;
import com.mmm.clout.advertisementservice.advertisements.domain.Campaign;
import com.mmm.clout.advertisementservice.common.msa.info.AdvertiserInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;

@Getter
@AllArgsConstructor
public class CampaignReaderResponse {

    private CampaignResponse campaign;
    private AdvertiserResponse advertiserInfo;


    public static CampaignReaderResponse from(CampaignReaderWithAdvertiser result) {
        return new CampaignReaderResponse(
            CampaignResponse.from(result.getCampaign()),
            AdvertiserResponse.from(result.getAdvertiserInfo())
        );
    }

    public static CustomPageResponse<CampaignReaderResponse> of(CampaignListReader campaigns) {
        Page<Campaign> campaignList = campaigns.getCampaignList();
        List<CampaignReader> campaignReaderList = campaignList.stream()
            .map(v -> new CampaignReader(v)).collect(Collectors.toList());
        AdvertiserInfo advertiserInfo = campaigns.getAdvertiserInfo();

        return new CustomPageResponse<>(
            campaignReaderList.stream().map(
                campaign -> CampaignReaderResponse.from(
                    new CampaignReaderWithAdvertiser(
                        campaign,
                        advertiserInfo
                    )
                )
            ).collect(Collectors.toList()),
            campaigns.getCampaignList().getNumber(),
            campaigns.getCampaignList().getSize(),
            campaigns.getCampaignList().getTotalPages(),
            campaigns.getCampaignList().getTotalElements()
        );

    }
}
