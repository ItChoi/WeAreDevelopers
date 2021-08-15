package com.wearedevs.common.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;

@Getter
@RequiredArgsConstructor
public enum DataCrudType {
    CREATE("create", "생성", HttpMethod.POST),
    SELECT("select", "조회", HttpMethod.GET),
    UPDATE("update", "수정", HttpMethod.PUT),
    DELETE("delete", "삭제", HttpMethod.DELETE);

    private final String code;
    private final String text;
    private final HttpMethod httpMethod;
}
