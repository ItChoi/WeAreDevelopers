package com.wearedevs.web.security;

import com.wearedevs.common.exception.user.UserNotFoundException;
import com.wearedevs.common.utils.SecurityUtil;
import com.wearedevs.web.login.dto.LoginRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Transactional
@Service
public class SecurityService {
    private final AuthenticationProvider authProvider;
    private final SessionAuthenticationStrategy sessionStrategy;

    public UsernamePasswordAuthenticationToken getUsernamePwTokenByLoginInfo(LoginRequestDto requestDto) {
        String username = requestDto.getUsername();
        username = username.trim();
        String inputPassword = requestDto.getPassword();
        if (isNotEmptyUsernameOrPassword(username, inputPassword)) throw new UserNotFoundException("계정 정보를 정확히 입력해주세요.");

        return new UsernamePasswordAuthenticationToken(username, inputPassword);
    }

    private boolean isNotEmptyUsernameOrPassword(String username, String password) {
        return !StringUtils.hasText(username) || !StringUtils.hasText(password);
    }

    public Authentication getAuthenticationByCustomProvider(UsernamePasswordAuthenticationToken token) {
        return authProvider.authenticate(token);
    }

    public void setSecurityContextAuthentication(Authentication authentication) {
        SecurityContext securityContext = SecurityUtil.getSecurityContext().orElse(null);
        if (securityContext != null) securityContext.setAuthentication(authentication);
    }

    public void setSessionAuthByStrategy(Authentication authentication, HttpServletRequest req, HttpServletResponse res) {
        sessionStrategy.onAuthentication(authentication, req, res);
    }


}
