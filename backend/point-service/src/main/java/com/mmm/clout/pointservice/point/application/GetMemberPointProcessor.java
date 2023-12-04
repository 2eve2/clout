package com.mmm.clout.pointservice.point.application;

import com.mmm.clout.pointservice.point.domain.Point;
import com.mmm.clout.pointservice.point.domain.repository.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class GetMemberPointProcessor {

    private final PointRepository pointRepository;

    @Transactional
    public Point execute(Long memberId) {
        return pointRepository.findByMemberId(memberId)
            .orElseGet(() -> pointRepository.save(Point.create(memberId, 0L)));
    }
}
