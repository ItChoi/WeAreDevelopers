package com.wearedevs.api.user.dto;

import com.wearedevs.common.enumeration.user.GenderType;
import com.wearedevs.common.enumeration.user.LoginAccessType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private Long id; // 사용자 PK
    private LoginAccessType loginType; // 사용자 로그인 타입 (BASIC, KAKAO, GOOGLE, NAVER, ...)
    private String loginId; // 로그인 아이디
    private String password; // 비밀번호
    private String name; // 이름
    private String email; // 이메일
    private String phoneNumber; // 핸드폰 번호
    private String profileImagePath; // 프로필 사진 경로
    private GenderType gender; // 성별 (여성: F, 남성: M, 비공개: S)
    private String birthday; // 생년월일

    @Builder
    public UserDto(Long id, LoginAccessType loginType, String loginId, String password, String name, String email, String phoneNumber, String profileImagePath, GenderType gender, String birthday) {
        this.id = id;
        this.loginType = loginType;
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.profileImagePath = profileImagePath;
        this.gender = gender;
        this.birthday = birthday;
    }
}
