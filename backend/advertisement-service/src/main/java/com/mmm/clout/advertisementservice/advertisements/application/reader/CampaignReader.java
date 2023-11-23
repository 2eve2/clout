package com.mmm.clout.advertisementservice.advertisements.application.reader;

import com.mmm.clout.advertisementservice.advertisements.domain.AdCategory;
import com.mmm.clout.advertisementservice.advertisements.domain.AdPlatform;
import com.mmm.clout.advertisementservice.advertisements.domain.Campaign;
import com.mmm.clout.advertisementservice.advertisements.domain.Region;
import com.mmm.clout.advertisementservice.image.domain.AdvertiseSign;
import com.mmm.clout.advertisementservice.image.domain.Image;
import com.mmm.clout.advertisementservice.image.presentation.ImageResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public class CampaignReader {

    private Long campaignId;

    private String title;

    private Long price;

    private String details;

    private AdCategory adCategory; // 광고 카테고리

    private Boolean isPriceChangeable; // 광고비 협의 여부

    private Boolean isDeliveryRequired; // 배송 여부

    private Integer numberOfRecruiter; // 모집인원

    private Integer numberOfApplicants; // 신청인원

    private Integer numberOfSelectedMembers; // 채택 인원

    private String offeringDetails; // 제공 내역 설명

    private String sellingLink; // 판매처 링크 (선택사항)

    private LocalDate applyStartDate; // 모집 시작 날짜

    private LocalDate applyEndDate; // 모집 종료 날짜

    private Integer minClouterAge; // 최소 클라우터 나이

    private Integer maxClouterAge; // 최대 클라우터 나이

    private Integer minFollower; // 최소 팔로워 수

    private Boolean isEnded; // 모집 종료 여부

    private List<String> adPlatformList = new ArrayList<>();

    private List<String> regionList = new ArrayList<>();

    private List<ImageResponse> imageList = new ArrayList<>();

    private AdvertiseSign advertiserSign;

    private LocalDateTime deleteAt;

    private Long advertiserId;

    public CampaignReader(Campaign campaign) {
        this.campaignId = campaign.getId();
        this.title = campaign.getTitle();

        this.price = campaign.getPrice();
        this.details = campaign.getDetails();
        this.deleteAt = campaign.getDeletedAt();
        this.advertiserId = campaign.getAdvertiserId();
        this.adCategory = campaign.getAdCategory();
        this.isPriceChangeable = campaign.getIsPriceChangeable();
        this.isDeliveryRequired = campaign.getIsDeliveryRequired();
        this.numberOfRecruiter = campaign.getNumberOfRecruiter();
        this.numberOfApplicants = campaign.getNumberOfApplicants();
        this.numberOfSelectedMembers = campaign.getNumberOfSelectedMembers();
        this.offeringDetails = campaign.getOfferingDetails();
        this.sellingLink = campaign.getSellingLink();
        this.applyStartDate = campaign.getApplyStartDate();
        this.applyEndDate = campaign.getApplyEndDate();
        this.minClouterAge = campaign.getMinClouterAge();
        this.maxClouterAge = campaign.getMaxClouterAge();
        this.minFollower = campaign.getMinFollower();
        this.isEnded = campaign.getIsEnded();
        this.adPlatformList = campaign.getAdPlatformList().stream().map(v -> v.toString()).collect(Collectors.toList());
        this.regionList = campaign.getRegionList().stream().map(v -> v.toString()).collect(Collectors.toList());;
        this.imageList = campaign.getImageList().stream().map(v -> new ImageResponse(v)).collect(Collectors.toList());
        this.advertiserSign = campaign.getAdvertiserSign();
    }
}
