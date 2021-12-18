package com.wearedevs.common.enumeration.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserAuthority {
    SUPERVISOR("최고 관리자", "ROLE_SUPERVISOR"),
    MANAGER("관리자", "ROLE_MANAGER"),
    USER("사용자", "ROLE_USER"),
    ANONYMOUS("익명", "ROLE_ANONYMOUS"),
    TEST("테스트 계정", "ROLE_TEST");

    private final String codeName;
    private final String code;
}
