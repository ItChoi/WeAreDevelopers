package com.wearedevs.common.enumeration.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserAuthority {
    SUPERVISOR("최고 관리자", "supervisor", "ROLE_SUPERVISOR"),
    MANAGER("관리자", "manager", "ROLE_MANAGER"),
    USER("사용자", "user", "ROLE_USER"),
    ANONYMOUS("익명", "anonymous", "ROLE_ANONYMOUS");

    private final String codeName;
    private final String code;
    private final String fullCode;
}
