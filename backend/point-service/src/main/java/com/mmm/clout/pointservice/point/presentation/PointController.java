package com.mmm.clout.pointservice.point.presentation;

import com.mmm.clout.pointservice.common.docs.PointControllerDocs;
import com.mmm.clout.pointservice.point.application.facade.PointFacade;
import com.mmm.clout.pointservice.point.domain.PointTransaction;
import com.mmm.clout.pointservice.point.presentation.request.ChargePointRequest;
import com.mmm.clout.pointservice.point.presentation.request.ReducePointRequest;
import com.mmm.clout.pointservice.point.presentation.request.WithdrawPointRequest;
import com.mmm.clout.pointservice.point.presentation.response.ChargePointResponse;
import com.mmm.clout.pointservice.point.presentation.response.GetMemberTotalPointResponse;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/points")
@RequiredArgsConstructor
public class PointController implements PointControllerDocs {

    private final PointFacade pointFacade;

    /**
     * 충전
     */
    @PostMapping("/charge")
    public ResponseEntity<ChargePointResponse> charge(
        @Valid @RequestBody ChargePointRequest request
    ) {
        ChargePointResponse result = ChargePointResponse.from(
            pointFacade.charge(request.toCommand()),
            request.getChargePoint());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * 차감 (포인트 사용)
     */
    @PostMapping("/reduce")
    public ResponseEntity<Void> reduce(
        @Valid @RequestBody ReducePointRequest request
    ) {
        pointFacade.reduce(request.toCommand());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 출금
     */
    @PostMapping("/withdrawal")
    public ResponseEntity<Void> withdraw(
        @Valid @RequestBody WithdrawPointRequest request
    ) {
        pointFacade.withdraw(request.toCommand());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 유저 포인트 잔액 조회
     */
    @GetMapping
    public ResponseEntity<GetMemberTotalPointResponse> getMemberPoint(
        @RequestParam Long memberId
    ) {
        GetMemberTotalPointResponse result = GetMemberTotalPointResponse.from(
            pointFacade.getMemberPoint(memberId));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/transactions?memberId={memberId}&category={내역category}")
    public ResponseEntity<Void> getTransactionListByType(
        @RequestParam Long memberId,
        @RequestParam String category
    ) {
        List<PointTransaction> result = pointFacade.getTransactionListByCategory(memberId,
            category);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
