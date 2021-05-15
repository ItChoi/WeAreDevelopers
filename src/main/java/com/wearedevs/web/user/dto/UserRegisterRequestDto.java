package com.wearedevs.web.user.dto;

import com.sun.istack.NotNull;
import com.wearedevs.common.enumeration.user.LoginType;
import com.wearedevs.common.enumeration.user.UserActiveStatus;
import com.wearedevs.common.enumeration.user.UserAuthority;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRegisterRequestDto {
    @NotNull
    private String loginId;
    @NotNull
    private String password;
    private String name;
    private String email;
    private String profileImageName;
    private String introduce;
    private String phoneNumber;
    private LoginType loginType;
    private UserAuthority userAuthority;
}
