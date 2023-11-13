package com.mmm.clout.contractservice.contract.application.command;

import com.mmm.clout.contractservice.contract.domain.AdvertiserInfo;
import com.mmm.clout.contractservice.contract.domain.ClouterInfo;
import com.mmm.clout.contractservice.contract.domain.Contract;
import com.mmm.clout.contractservice.contract.domain.info.SelectAdrInfo;
import com.mmm.clout.contractservice.contract.domain.info.SelectClrInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateContractCommand {

    private String name;

    private Long price;

    private String postDeadline;

    private String contractExpiration;

    private String contents;

    private Long clouterId;

    private Long advertiserId;

    public Contract toEntity(SelectClrInfo clouter, SelectAdrInfo advertiser) {
        return Contract.create(
              this.name,
              this.price,
              this.postDeadline,
              this.contractExpiration,
              this.contents,
              new ClouterInfo(clouter),
              new AdvertiserInfo(advertiser)
        );
    }
}