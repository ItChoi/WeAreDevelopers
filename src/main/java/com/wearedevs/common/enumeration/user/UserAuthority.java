package com.wearedevs.common.enumeration.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserAuthority {
    SUPERVISOR("최고 관리자", "supervisor"),
    MANAGER("관리자", "manager"),
    USER("사용자", "user"),
    ANONYMOUS("익명", "anonymous");

    private final String codeName;
    private final String code;
}
