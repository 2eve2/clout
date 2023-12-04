package com.mmm.clout.memberservice.clouter.application;

import com.mmm.clout.memberservice.clouter.application.reader.ClouterReader;
import com.mmm.clout.memberservice.clouter.domain.Clouter;
import com.mmm.clout.memberservice.clouter.domain.repository.ClouterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class SelectTop10ClouterProcessor {

    private final ClouterRepository clouterRepository;

    @Transactional
    public List<ClouterReader> execute() {
        List<Clouter> clouters = clouterRepository.findTop10ByOrderByAvgScoreDesc();
        return clouters.stream().map(v -> new ClouterReader(v)).collect(Collectors.toList());
    }

}
