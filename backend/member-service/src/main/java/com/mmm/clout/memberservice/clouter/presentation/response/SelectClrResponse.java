package com.mmm.clout.memberservice.clouter.presentation.response;

import com.mmm.clout.memberservice.clouter.application.reader.ClouterReader;
import com.mmm.clout.memberservice.common.Category;
import com.mmm.clout.memberservice.common.Region;
import com.mmm.clout.memberservice.common.Role;
import com.mmm.clout.memberservice.common.entity.address.response.AddressResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class SelectClrResponse {

    @Schema(description = "클라우터 멤버 아이디")
    private Long clouterId;

    @Schema(description = "클라우터 유저 아이디")
    private String userId;

    @Schema(description = "클라우터 별점 평균")
    private Long avgScore;

    @Schema(description = "클라우터 유저 role (USER, ADMIN)")
    private Role role;

    @Schema(description = "클라우터 닉네임임")
    private String nickName;

    @Schema(description = "이름")
    private String name;

    @Schema(description = "생일")
    private LocalDate birthday;

    @Schema(description = "나이")
    private Integer age;

    @Schema(description = "전화번호")
    private String phoneNumber;

    @Schema(description = "개인 채널 리스트")
    private List<ChannelResponse> channelList;

    private HopeCostResponse hopeCost;

    @Schema(description = "네고 가능 여부")
    private boolean negoable;

    @Schema(description = "광고를 원하는 카테고리 목록")
    private List<String> categoryList;

    @Schema(description = "광고를 희망하는 지역 목록")
    private List<String> regionList;

    private AddressResponse address;

    public SelectClrResponse(ClouterReader clouterReader) {

        this.clouterId = clouterReader.getClouterId();

        this.userId = clouterReader.getUserId();

        this.avgScore = clouterReader.getAvgScore();

        this.role = clouterReader.getRole();

        this.nickName = clouterReader.getNickName();
        this.name = clouterReader.getName();
        this.birthday = clouterReader.getBirthday();
        this.age = clouterReader.getAge();
        this.phoneNumber = clouterReader.getPhoneNumber();
        this.channelList = clouterReader.getChannelList()
            .stream().map(ChannelResponse::new).collect(Collectors.toList());
        this.hopeCost = new HopeCostResponse(clouterReader.getHopeCost());
        this.negoable = clouterReader.isNegoable();
        this.categoryList = clouterReader.getCategoryList();
        this.regionList = clouterReader.getRegionList();
        this.address = new AddressResponse(clouterReader.getAddress());
    }

    public static SelectClrResponse from(ClouterReader clouterReader) {
        SelectClrResponse response = new SelectClrResponse(clouterReader);
        return response;
    }
}
