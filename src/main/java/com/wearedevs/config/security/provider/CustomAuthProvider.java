package com.wearedevs.config.security.provider;

import com.wearedevs.web.user.service.UserService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Component
public class CustomAuthProvider implements AuthenticationProvider {
//public class CustomAuthProvider extends DaoAuthenticationProvider {
    private final UserService userService;


    /*@Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("auth: " + authentication);
        // TOOD: JWT & UsernamePwAuth 체크 로직 여기에 들어가야 하나?
        // 캐시 확인 User 객체
        // UserDetails user = 캐시 확인
        // UsernameNotFoundException
        //UserDetails user = userService.loadUserByUsername(authentication.getName());
        return createSuccessAuthentication(null, authentication, null);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }*/

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("auth: " + authentication);
        /** TODO List
         * 1. support를 통해 유효한 Token 종류인지 확인
         * 2. loadUserByUsername 호출
         * 3. UsernamePasswordAuthenticationToken(principal, credentials, authorities) 생성
         * 4. password 포함된 것 null로 변경
         *
         * 1. JWT & UsernamePwAuth 체크 로직 여기에 들어가야 하나?
         * 2. 캐시 확인 User 객체
         * 3. UserDetails user = 캐시 확인
         * 4. UsernameNotFoundException
         *
         */

        UserDetails user = userService.loadUserByUsername(authentication.getName());

        return new UsernamePasswordAuthenticationToken(user, authentication.getCredentials(), user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
