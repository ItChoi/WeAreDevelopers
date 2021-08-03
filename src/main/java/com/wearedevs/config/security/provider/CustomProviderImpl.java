package com.wearedevs.config.security.provider;

import com.wearedevs.web.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomProviderImpl extends DaoAuthenticationProvider {
//public class CustomProviderImpl implements AuthenticationProvider {
    private final UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // TOOD: JWT & UsernamePwAuth 체크 로직 여기에 들어가야 하나?
        // 캐시 확인 User 객체
        log.info("auth: " + authentication);
        // UserDetails user = 캐시 확인

        // UsernameNotFoundException
        UserDetails user = userService.loadUserByUsername(authentication.getName());




        return createSuccessAuthentication(user, authentication, user);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }



}
