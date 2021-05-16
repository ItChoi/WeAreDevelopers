package com.wearedevs.web.user.dto;

import com.wearedevs.common.enumeration.user.LoginType;
import com.wearedevs.common.enumeration.user.UserAuthority;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
public class UserRegisterRequestDto {
    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password;
    private String name;
    private String email;
    private MultipartFile file;
    private String profileImageName;
    private String introduce;
    private String phoneNumber;
    private LoginType loginType;
    private UserAuthority authority;
}
