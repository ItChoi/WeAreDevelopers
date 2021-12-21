package com.wearedevs.filter;

import com.wearedevs.common.util.SecurityUtil;
import com.wearedevs.common.util.msg.ExceptionMsgUtil;
import com.wearedevs.security.token.AjaxAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AjaxLoginProcessingFilter extends AbstractAuthenticationProcessingFilter {
    public static final String AJAX_LOGIN_PROCESSING_URL = "/api/login";


    public AjaxLoginProcessingFilter() {
        // 사용자가 이 URL로 요청을 했을 때 지정 URL과 매칭이 되어야 필터가 작동하도록 설정
        super(new AntPathRequestMatcher(AJAX_LOGIN_PROCESSING_URL));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (!isAjaxRequest(request)) throw new IllegalStateException(ExceptionMsgUtil.NOT_AJAX_REQUEST);

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (!SecurityUtil.validateEmptyForUsernameAndPw(username, password)) {
            throw new IllegalArgumentException(ExceptionMsgUtil.INVALID_LOGIN_INFO);
        }

        AjaxAuthenticationToken ajaxAuthenticationToken = new AjaxAuthenticationToken(username, password);
        return getAuthenticationManager().authenticate(ajaxAuthenticationToken);
    }

    private boolean isAjaxRequest(HttpServletRequest request) {
        // 사용자가 요청 헤더에 Ajax 요청을 나타내는 값을 보내준다.
        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
            return true;
        }

        return false;
    }

    /*@Override
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }*/
}
