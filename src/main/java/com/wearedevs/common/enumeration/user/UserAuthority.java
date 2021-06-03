package com.wearedevs.common.enumeration.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserAuthority {
    SUPERVISOR("최고 관리자", "SUPERVISOR", "ROLE_SUPERVISOR"),
    MANAGER("관리자", "MANAGER", "ROLE_MANAGER"),
    USER("사용자", "USER", "ROLE_USER"),
    ANONYMOUS("익명", "ANONYMOUS", "ROLE_ANONYMOUS"),
    TEST("테스트 계정", "TEST", "ROLE_TEST");

    private final String codeName;
    private final String code;
    private final String fullCode;
}
