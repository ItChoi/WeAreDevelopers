package com.wearedevs.web.login.service;

import com.wearedevs.web.login.dto.LoginRequestDto;
import com.wearedevs.web.login.dto.LoginResponseDto;
import com.wearedevs.web.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class LoginServiceImpl implements LoginService {
    private final UserService userService;
    private final AuthenticationProvider provider;

    @Override
    public LoginResponseDto loginProcess(LoginRequestDto requestDto) {
        String username = requestDto.getUsername();
        String inputPassword = requestDto.getPassword();
        /**
         * 인증
         * 1. 스프링 시큐리티 자체 인증
         * 2. 프로젝트 로그인 인증 규칙
         */
        // Error Handler 만들기
        try {
            UserDetails userDetails = userService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(username, inputPassword, userDetails.getAuthorities());
            provider.authenticate(upat);
        } catch (Exception e) {

        }



        // 인가
        return null;
    }

    @Override
    public void changeLoginApproachKinds(LoginRequestDto requestDto, HttpServletRequest request) {
        // TODO: request를 통해 UsernamePwAuth || Jwt인지 체크후 세팅해주기.
        requestDto.setLoginApproachKinds(null);
    }
}
