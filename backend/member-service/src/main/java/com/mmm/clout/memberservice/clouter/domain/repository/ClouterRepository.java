package com.mmm.clout.memberservice.clouter.domain.repository;

import com.mmm.clout.memberservice.clouter.domain.Channel;
import com.mmm.clout.memberservice.clouter.domain.Clouter;
import com.mmm.clout.memberservice.common.Category;
import com.mmm.clout.memberservice.common.Region;

import java.util.List;

public interface ClouterRepository {

    Clouter save(Clouter clouter);

    Clouter findById(Long userId);

    List<Clouter> findTop10ByOrderByAvgScoreDesc();
}
