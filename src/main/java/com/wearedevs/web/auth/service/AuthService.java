package com.wearedevs.web.auth.service;

import com.wearedevs.common.dto.jwt.TokenResponseDto;
import com.wearedevs.web.login.dto.LoginRequestDto;

import org.springframework.http.HttpHeaders;

public interface AuthService {
    String createAuthJwtToken(LoginRequestDto loginRequestDto);
    HttpHeaders addJwtTokenOfBearerType(String jwt);
}
