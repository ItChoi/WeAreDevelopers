package com.wearedevs.handler.security;

import com.wearedevs.common.util.msg.ExceptionMsgUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        // 인증 검증 실패시 인증 예외 발생 - 예외 발생 후 사용자에게 에러 메시지 노출
        String errorMsg = getErrorMsgByExceptionType(exception);
        setDefaultFailureUrl("/login?error=true&exception=" + errorMsg);

        super.onAuthenticationFailure(request, response, exception);

    }

    private String getErrorMsgByExceptionType(AuthenticationException exception) {
        if (exception instanceof UsernameNotFoundException) return ExceptionMsgUtil.NOT_EXISTS_ACCOUNT;
        if (exception instanceof BadCredentialsException) return ExceptionMsgUtil.MISMATCH_PASSWORD;
        return ExceptionMsgUtil.INVALID_LOGIN_INFO;
    }
}
