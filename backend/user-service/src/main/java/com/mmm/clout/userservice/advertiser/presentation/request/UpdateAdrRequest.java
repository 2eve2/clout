package com.mmm.clout.userservice.advertiser.presentation.request;

import com.mmm.clout.userservice.advertiser.application.command.CreateAdrCommand;
import com.mmm.clout.userservice.advertiser.application.command.UpdateAdrCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class UpdateAdrRequest {

    @NotBlank
    private String userId;

    @NotBlank
    private String pwd;

    private AddressRequest address;

    private CompanyInfoRequest companyInfo;

    public UpdateAdrCommand toCommand(Long advertiserId) {
        return new UpdateAdrCommand(
                advertiserId,
                this.userId,
                this.pwd,
                this.address.toCommand(),
                this.companyInfo.toCommand()
        );
    }
}