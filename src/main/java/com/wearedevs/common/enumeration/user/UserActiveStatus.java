package com.wearedevs.common.enumeration.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserActiveStatus {
    ACTIVITY("활동", "activity"),
    INACTIVITY("비활동", "inactivity"),
    DORMANCY("휴면", "dormancy"),
    WITHDRAWAL("회원탈퇴", "withdrawal"),
    EXIT("퇴장", "exit"),
    FORCED_EXIT("강퇴", "forcedExit");

    private final String text;
    private final String code;
}
