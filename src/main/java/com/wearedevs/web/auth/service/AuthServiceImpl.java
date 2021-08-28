package com.wearedevs.web.auth.service;

import com.wearedevs.common.utils.jwt.TokenProvider;
import com.wearedevs.config.filter.JwtFilter;
import com.wearedevs.web.login.dto.LoginRequestDto;
import com.wearedevs.web.security.SecurityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Slf4j
@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private final TokenProvider tokenProvider;
    //private final AuthenticationManagerBuilder authenticationManagerBuilder;
    //private final AuthenticationProvider authProvider;
    private final SecurityService securityService;

    @Override
    public String createAuthJwtToken(LoginRequestDto loginRequestDto) {
        UsernamePasswordAuthenticationToken authToken = securityService.getUsernamePwTokenByLoginInfo(loginRequestDto);
        Authentication authentication = securityService.getAuthenticationByCustomProvider(authToken);
        securityService.setSecurityContextAuthentication(authentication);

        return tokenProvider.createToken(authentication);
    }

    @Override
    public HttpHeaders addJwtTokenOfBearerType(String jwt) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        return httpHeaders;
    }
}
