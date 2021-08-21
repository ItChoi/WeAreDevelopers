package com.wearedevs.common.enumeration.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum WorkStatus {
    WORK("work", "재직"),
    PREPARE("prepare", "취업 준비"),
    MOVE("move", "이직");

    private final String code;
    private final String text;

}
