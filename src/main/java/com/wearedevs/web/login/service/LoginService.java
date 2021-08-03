package com.wearedevs.web.login.service;

import com.wearedevs.web.login.dto.LoginRequestDto;
import com.wearedevs.web.login.dto.LoginResponseDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginService {
    LoginResponseDto loginProcess(LoginRequestDto requestDto, HttpServletRequest req, HttpServletResponse res) throws Exception;
    void changeLoginApproachKinds(LoginRequestDto requestDto, HttpServletRequest request);
}
