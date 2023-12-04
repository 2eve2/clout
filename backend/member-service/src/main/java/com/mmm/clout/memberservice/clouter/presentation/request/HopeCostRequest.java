package com.mmm.clout.memberservice.clouter.presentation.request;

import com.mmm.clout.memberservice.clouter.application.command.HopeCostCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HopeCostRequest {

    @NotNull
    @Schema(description = "원하는 최소 금액")
    private Long minCost;


    public HopeCostCommand toCommand() {
        return new HopeCostCommand(
            this.minCost
        );
    }
}
