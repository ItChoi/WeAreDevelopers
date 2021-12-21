package com.wearedevs.common.enumeration.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRequireInfo {
    USERNAME("아이디"),
    PASSWORD("비밀번호");

    private final String text;
}
