package com.wearedevs.web.login.dto;

import com.wearedevs.common.enumeration.user.LoginType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class LoginRequestDto {
    @NotEmpty
    @Size(min = 3, max = 20)
    private String username;
    
    // TODO 정규식 사용
    @NotEmpty
    @Size(min = 3, max = 300)
    private String password;

    private String email;
    private LoginType loginType;

    @Builder
    public LoginRequestDto(String username, String password, String email, LoginType loginType) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.loginType = loginType;
    }
}
