package com.wearedevs.handler.security;

import com.wearedevs.common.util.msg.ExceptionMsgUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class AjaxAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        // 인증 검증 실패시 인증 예외 발생 - 예외 발생 후 사용자에게 에러 메시지 노출
        String errorMsg = getErrorMsgByExceptionType(exception);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        // objectMapper.writeValue(response.getWrite(), userSecurity);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));
        bw.write(errorMsg);
    }

    // TODO: 공통 코드로 만들기
    private String getErrorMsgByExceptionType(AuthenticationException exception) {
        if (exception instanceof UsernameNotFoundException) return ExceptionMsgUtil.NOT_EXISTS_ACCOUNT;
        if (exception instanceof BadCredentialsException) return ExceptionMsgUtil.MISMATCH_PASSWORD;
        return ExceptionMsgUtil.INVALID_LOGIN_INFO;
    }
}
