package com.wearedevs.web.user.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRegisterRequestDto {
    private String loginId;
    private String password;
    private String name;
    private String email;
    private String profileImageName;
    private String introduce;
    private String phoneNumber;
}
