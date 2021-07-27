package com.wearedevs.config.security.provider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomProviderImpl implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // TOOD: JWT & UsernamePwAuth 체크 로직 여기에 들어가야 하나?

        log.info("auth: " + authentication);
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
