package com.mmm.clout.pointservice.point.presentation.request;

import com.mmm.clout.pointservice.point.application.command.ChargeCommand;
import com.mmm.clout.pointservice.point.domain.PaymentType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChargePointRequest {

    @NotNull
    private Long memberId;

    @NotNull
    @Size(min = 1000)
    private Long chargePoint;

    @NotBlank
    private PaymentType paymentType;

    public ChargeCommand toCommand() {
        return new ChargeCommand(memberId, chargePoint, paymentType);
    }
}
