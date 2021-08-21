package com.wearedevs.common.enumeration.account;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AccountType {
    LOCK("lock", "잠김"),
    UNLOCK("unlock", "안잠김");

    private final String code;
    private final String text;


}
