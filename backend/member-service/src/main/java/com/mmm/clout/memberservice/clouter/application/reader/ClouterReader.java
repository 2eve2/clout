package com.mmm.clout.memberservice.clouter.application.reader;

import com.mmm.clout.memberservice.clouter.domain.Clouter;
import com.mmm.clout.memberservice.common.Category;
import com.mmm.clout.memberservice.common.Region;
import com.mmm.clout.memberservice.common.Role;
import com.mmm.clout.memberservice.common.entity.address.reader.AddressReader;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@Getter
public class ClouterReader {

    private Long clouterId;

    private String userId;

    private Long avgScore;

    private Role role;

    private String nickName;

    private String name;

    private LocalDate birthday;

    private Integer age;

    private String phoneNumber;

    private List<ChannelReader> channelList = new ArrayList<>();

    private HopeCostReader hopeCost;

    private boolean negoable;

    private List<String> categoryList = new ArrayList<>();

    private List<String> regionList = new ArrayList<>();

    private AddressReader address;

    public ClouterReader(Clouter clouter) {

        this.clouterId = clouter.getId();
        this.userId = clouter.getUserId();
        this.avgScore = clouter.getAvgScore();
        this.role = clouter.getRole();
        this.nickName = clouter.getNickName();
        this.name = clouter.getName();
        this.birthday = clouter.getBirthday();
        this.age = clouter.getAge();
        this.phoneNumber = clouter.getPhoneNumber();
        this.channelList = clouter.getChannelList()
            .stream().map(ChannelReader::new).collect(Collectors.toList());
        this.hopeCost = new HopeCostReader(clouter.getHopeCost());
        this.negoable = clouter.isNegoable();
        this.categoryList = clouter.allGetCategoryList().stream().map(v->v.toString()).collect(Collectors.toList());
        this.regionList = clouter.allGetRegionList().stream().map(v->v.toString()).collect(Collectors.toList());;
        this.address = new AddressReader(clouter.getAddress());
    }

}
