package com.wearedevs.common.enumeration.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LoginType {
    LOGIN("로그인", "login"),
    LOGOUT("로그아웃", "logout");

    private final String text;
    private final String code;
}
