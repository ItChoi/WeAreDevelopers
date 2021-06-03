package com.wearedevs.config.filter;

import com.wearedevs.common.dto.session.SessionUser;
import com.wearedevs.common.enumeration.user.LoginType;
import com.wearedevs.common.utils.jwt.TokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class JwtFilter extends GenericFilterBean {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    private TokenProvider tokenProvider;

    public JwtFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // JWT 토큰의 인증 정보를 현재 실행 중인 Security Context에 저장하는 역할을 수행한다.
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String jwt = resolveToken(httpServletRequest);
        String requestURI = httpServletRequest.getRequestURI();
        /*if (!StringUtils.hasText(jwt)) {
            SessionUser findUserBySession = findOAuth2InfoAtSession(httpServletRequest);
            if (isAvailableOAuth2JwtToken(findUserBySession)) {
                createOAuth2JwtToken(findUserBySession);
            }
        }*/

        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
            Authentication authentication = tokenProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.debug("Security Context에 '{}' 인증 정보를 저장했습니다. uri: {}", authentication.getName(), requestURI);
        } else {
            log.debug("유효한 JWT 토큰이 없습니다. uri: {}", requestURI);
        }

        chain.doFilter(request, response);
    }

    private boolean isAvailableOAuth2JwtToken(SessionUser findUserBySession) {
        return existsOAuth2Info(findUserBySession);
    }

    private SessionUser findOAuth2InfoAtSession(HttpServletRequest request) {
        return (SessionUser) request.getSession().getAttribute("user");
    }


    // 토큰 정보 꺼내오기.
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        return null;
    }

    private boolean existsOAuth2Info(SessionUser findUserBySession) {
        if (findUserBySession == null) return false;
        String email = findUserBySession.getEmail();
        LoginType loginType = findUserBySession.getLoginType();

        return StringUtils.hasText(email) && loginType != null;
    }
}
