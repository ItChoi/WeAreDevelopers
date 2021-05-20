package com.wearedevs.web.user.dto;

import com.wearedevs.common.enumeration.user.LoginType;
import com.wearedevs.common.enumeration.user.UserAuthority;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDetailInfoResponseDto {
    private String loginId;
    private String name;
    private String email;
    private String profileImageName;
    private String introduce;
    private String phoneNumber;
    private LoginType loginType;
    private UserAuthority authority;

    @Builder
    public UserDetailInfoResponseDto(String loginId, String name, String email, String profileImageName, String introduce, String phoneNumber, LoginType loginType, UserAuthority authority) {
        this.loginId = loginId;
        this.name = name;
        this.email = email;
        this.profileImageName = profileImageName;
        this.introduce = introduce;
        this.phoneNumber = phoneNumber;
        this.loginType = loginType;
        this.authority = authority;
    }
}
