package com.wearedevs.common.enumeration.hashtag;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum HashTagType {
    USER_DETAIL("userDetail", "사용자 상세 페이지");

    private final String type;
    private final String explanation;
}
