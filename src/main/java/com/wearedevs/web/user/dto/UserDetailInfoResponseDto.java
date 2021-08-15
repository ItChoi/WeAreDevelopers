package com.wearedevs.web.user.dto;

import com.wearedevs.common.enumeration.user.LoginType;
import com.wearedevs.common.enumeration.user.UserAuthority;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserDetailInfoResponseDto {
    private String loginId;
    private String name;
    private String email;
    private String profileImagePath;
    private String profileThumbnailImagePath;
    private String introduce;
    private String phoneNumber;
    private LoginType loginType;
    private List<UserAuthority> userAuthorityList;

    @Builder
    public UserDetailInfoResponseDto(String loginId, String name, String email, String profileImagePath, String profileThumbnailImagePath, String introduce, String phoneNumber, LoginType loginType, List<UserAuthority> userAuthorityList) {
        this.loginId = loginId;
        this.name = name;
        this.email = email;
        this.profileImagePath = profileImagePath;
        this.profileThumbnailImagePath = profileThumbnailImagePath;
        this.introduce = introduce;
        this.phoneNumber = phoneNumber;
        this.loginType = loginType;
        this.userAuthorityList = userAuthorityList;
    }
}
