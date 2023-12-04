package com.mmm.clout.pointservice.point.presentation.request;

import com.mmm.clout.pointservice.point.application.command.WithdrawCommand;
import com.mmm.clout.pointservice.point.domain.BankType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WithdrawPointRequest {

    @NotBlank
    private String bankType; // 은행 종류

    @NotNull
    private Long accountNumber; // 계좌번호

    @NotNull
    private Long withdrawingPoint; // 출금할 포인트 (신청한 포인트)

    @NotNull
    private Long commission; // 수수료

    @NotNull
    @Size(min = 100)
    private Long totalReducingPoint; // 총차감금액

    public WithdrawCommand toCommand() {
        return new WithdrawCommand(
            BankType.converToEnum(bankType),
            accountNumber,
            withdrawingPoint,
            commission,
            totalReducingPoint
        );
    }
}

/*

1. 은행 종류
2. 계좌번호
3. 출금할 포인트 (신청한 포인트)
4. 수수료 (신청한 포인트 * 0.07)
5. 총차감금액 (3+4 합친 금액)
- 최소 신청 금액 100원
- 최대는 보유하고 있는 포인트 금액
를 넘겨줌
(기존 포인트랑 비교해서 보여주기)

 */