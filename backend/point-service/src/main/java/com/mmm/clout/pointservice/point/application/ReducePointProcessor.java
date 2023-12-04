package com.mmm.clout.pointservice.point.application;

import com.mmm.clout.pointservice.point.domain.Point;
import com.mmm.clout.pointservice.point.domain.PointCategory;
import com.mmm.clout.pointservice.point.domain.PointTransaction;
import com.mmm.clout.pointservice.point.domain.exception.PointNotFoundException;
import com.mmm.clout.pointservice.point.domain.repository.PointRepository;
import com.mmm.clout.pointservice.point.domain.repository.PointTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class ReducePointProcessor {

    private final PointRepository pointRepository;
    private final PointTransactionRepository pointTransactionRepository;


    @Transactional
    public void execute(Long memberId, Long reducingPoint, PointCategory pointCategory) {
        Point point = pointRepository.findByMemberId(memberId)
            .orElseGet(() -> pointRepository.save(Point.create(memberId, 0L)));
        point.reducePoint(reducingPoint);
        PointTransaction usedPointTsx = PointTransaction.usePoint(point, memberId, pointCategory);
        pointTransactionRepository.save(usedPointTsx);
    }
}
