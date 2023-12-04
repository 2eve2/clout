package com.mmm.clout.pointservice.point.domain.repository;

import com.mmm.clout.pointservice.point.domain.Point;
import com.mmm.clout.pointservice.point.domain.PointCategory;
import com.mmm.clout.pointservice.point.domain.PointTransaction;
import java.util.List;

public interface PointTransactionRepository {

    void save(PointTransaction chargedPtx);

    List<PointTransaction> findByPointAndPointCategory(Point point, PointCategory category);

}
