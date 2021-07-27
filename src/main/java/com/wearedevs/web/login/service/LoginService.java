package com.wearedevs.web.login.service;

import com.wearedevs.web.login.dto.LoginRequestDto;
import com.wearedevs.web.login.dto.LoginResponseDto;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {
    LoginResponseDto loginProcess(LoginRequestDto requestDto);
    void changeLoginApproachKinds(LoginRequestDto requestDto, HttpServletRequest request);
}
