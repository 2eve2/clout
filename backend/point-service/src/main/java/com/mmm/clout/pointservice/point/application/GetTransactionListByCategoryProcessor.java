package com.mmm.clout.pointservice.point.application;

import com.mmm.clout.pointservice.point.domain.PointCategory;
import com.mmm.clout.pointservice.point.domain.exception.EmptyTransactionException;
import com.mmm.clout.pointservice.point.domain.Point;
import com.mmm.clout.pointservice.point.domain.PointTransaction;
import com.mmm.clout.pointservice.point.domain.repository.PointRepository;
import com.mmm.clout.pointservice.point.domain.repository.PointTransactionRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class GetTransactionListByCategoryProcessor {

    private final PointRepository pointRepository;
    private final PointTransactionRepository pointTransactionRepository;

    @Transactional
    public List<PointTransaction> execute(Long memberId, String category) {
        Point point = pointRepository.findByMemberId(memberId)
            .orElseGet(() -> pointRepository.save(Point.create(memberId, 0L)));
        List<PointTransaction> ptxList =
            pointTransactionRepository.findByPointAndPointCategory(point, PointCategory.convertToEnum(category));
        if (ptxList.size() == 0) {
            throw new EmptyTransactionException();
        }
        return ptxList;
    }
}
