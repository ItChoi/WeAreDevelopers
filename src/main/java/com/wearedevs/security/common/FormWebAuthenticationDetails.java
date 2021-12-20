package com.wearedevs.security.common;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

// 사용자 인증 부가 정보를 저장하는 클래스 - - formLogin 방식 (jwt나 다른 토큰 방식도 되는지 확인 필요)
public class FormWebAuthenticationDetails extends WebAuthenticationDetails {
    private final String secretKey;

    public FormWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        this.secretKey = request.getParameter("secret_key");
    }

    public String getSecretKey() {
        return this.secretKey;
    }
}
