package com.wearedevs.config.provider;

import com.wearedevs.common.enumeration.user.UserAuthority;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class CustomAuthProvider implements AuthenticationProvider {
    //private final UserService userService;
    private static final String ANONYMOUS_USER = "anonymousUser";



    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("auth: " + authentication);
        if (!supports(authentication.getClass())) {
            // 익명 사용자 리턴 - 익명 사용자도 일부 사이트 사용가능하게 하기 위함
            return new AnonymousAuthenticationToken(
                    UserAuthority.ANONYMOUS.getCode(),
                    ANONYMOUS_USER,
                    AuthorityUtils.createAuthorityList(UserAuthority.ANONYMOUS.getCode())
            );
        }
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

        //UserDetails user = userService.loadUserByUsername(authentication.getName());


        //return new UsernamePasswordAuthenticationToken(user, authentication.getCredentials(), user.getAuthorities());
        return new UsernamePasswordAuthenticationToken(null, authentication.getCredentials(), null);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return isValidApplicationToken(authentication);
    }

    private boolean isValidApplicationToken(Class<?> authentication) {
        // TODO: 토큰 인스턴스 추가 될 때 허용 토큰 지정
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication)
                || OAuth2AuthenticationToken.class.isAssignableFrom(authentication);
    }

    /*public void eraseCredentialsaaaa(Authentication authentication) {

    }*/

}
