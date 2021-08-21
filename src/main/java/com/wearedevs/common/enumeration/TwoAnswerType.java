package com.wearedevs.common.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TwoAnswerType {
    Y("Y", "YES", true),
    N("N", "NO", false);


    private final String strShortCode;
    private final String strFullCode;
    private final boolean boolCode;
}
