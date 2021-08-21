package com.wearedevs.web.login.dto;

import com.wearedevs.common.enumeration.user.LoginAccessType;
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
    /**
     * 1. UsernamePasswordAuthentication 방식
     * 2. api 호출 방식 JWT (추후 적용)
     */
    @NotEmpty
    @Size(min = 3, max = 20)
    private String username;
    
    // TODO 정규식 사용
    @NotEmpty
    @Size(min = 3, max = 300)
    private String password;

    private String email;
    private LoginAccessType loginAccessType;
    // UsernamePwAuth || Jwt
    private LoginApproachKinds loginApproachKinds;

    @Builder
    public LoginRequestDto(String username, String password, String email, LoginAccessType loginAccessType) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.loginAccessType = loginAccessType;
    }
}
