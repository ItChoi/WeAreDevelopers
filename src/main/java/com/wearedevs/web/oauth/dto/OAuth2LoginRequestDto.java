package com.wearedevs.web.oauth.dto;

import com.wearedevs.common.enumeration.user.LoginAccessType;
import com.wearedevs.web.login.dto.LoginApproachKinds;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OAuth2LoginRequestDto {
    // OAuth2LoginAuthentication 방식
    private String email;
    private LoginAccessType loginAccessType;
    // UsernamePwAuth || Jwt
    private LoginApproachKinds loginApproachKinds;
}
