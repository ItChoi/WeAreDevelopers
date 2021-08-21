package com.wearedevs.common.enumeration.hashtag;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum HashTagType {
    /**
     * 해시 태그
     * 1. 사용 영역 동적으로 추가
     * 2. 어떤 페이지에서 사용 됐는지 
     */
    USER_DETAIL("userDetail", "사용자 상세 페이지");

    private final String type;
    private final String explanation;
}
