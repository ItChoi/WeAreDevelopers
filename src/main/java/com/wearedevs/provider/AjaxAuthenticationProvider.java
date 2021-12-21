package com.wearedevs.provider;

import com.wearedevs.api.user.dto.UserSecurity;
import com.wearedevs.api.user.service.UserService;
import com.wearedevs.common.util.SecurityUtil;
import com.wearedevs.common.util.msg.ExceptionMsgUtil;
import com.wearedevs.security.token.AjaxAuthenticationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
@Component
public class AjaxAuthenticationProvider implements AuthenticationProvider {
    private static final String ANONYMOUS_USER = "anonymousUser";
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        if (!SecurityUtil.validateEmptyForUsernameAndPw(username, password)) {
            throw new IllegalArgumentException(ExceptionMsgUtil.INVALID_LOGIN_INFO);
        }

        UserSecurity userSecurity = (UserSecurity) userService.loadUserByUsername(username.trim());


        if (!StringUtils.hasText(password) || !passwordEncoder.matches(password, userSecurity.getPassword())) {
            throw new BadCredentialsException(ExceptionMsgUtil.MISMATCH_PASSWORD);
        }

        // TODO: 부가 정보 클래스(FormWebAuthenticationDetails)를 만들고 부가 정보에 대한 검증 코드 만들기 - 예외: throw new InsufficientAuthenticationException()

        // TODO: Entity말고 dto형식으로 저장하기
        return new AjaxAuthenticationToken(userSecurity.getUser(), null, userSecurity.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return isValidApplicationToken(authentication);
    }

    private boolean isValidApplicationToken(Class<?> authentication) {
        return authentication.equals(AjaxAuthenticationToken.class);
    }
}
