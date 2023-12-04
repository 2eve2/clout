package com.mmm.clout.pointservice.point.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PointStatus {

    PLUS("+"), MINUS("-");

    private final String description;

}
