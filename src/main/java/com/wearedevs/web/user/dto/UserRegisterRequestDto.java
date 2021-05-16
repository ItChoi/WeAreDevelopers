package com.wearedevs.web.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wearedevs.common.enumeration.user.LoginType;
import com.wearedevs.common.enumeration.user.UserAuthority;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Setter
@Getter
public class UserRegisterRequestDto {
    @NotEmpty
    @Size(min = 3, max = 20)
    private String loginId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotEmpty
    @Size(min = 3, max = 300)
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
