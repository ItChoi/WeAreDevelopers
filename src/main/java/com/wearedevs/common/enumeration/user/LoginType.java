package com.wearedevs.common.enumeration.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LoginType {
    BASIC("홈페이지 가입", "basic"),
    KAKAO("카카오", "kakao"),
    GOOGLE("구글", "google"),
    NAVER("네이버", "naver"),
    GITHUB("깃헙", "github"),
    FACEBOOK("페이스북", "facebook"),
    INSTAGRAM("인스타그램", "instagram");

    private final String text;
    private final String code;

    public static LoginType convertByCode(String code) {
        for (LoginType loginType : LoginType.values()) {
            if (code.equals(loginType)) {
                return loginType;
            }
        }

        return BASIC;
    }
}
