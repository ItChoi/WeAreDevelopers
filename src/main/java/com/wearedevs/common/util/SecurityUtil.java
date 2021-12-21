package com.wearedevs.common.util;

import com.wearedevs.common.util.msg.ExceptionMsgUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Slf4j
public class SecurityUtil {
    public static Authentication getAuthentication() {
        SecurityContext securityContext = getSecurityContext().orElse(null);
        return securityContext == null ? null : securityContext.getAuthentication();
    }

    public static boolean isNotEmptyAuthPrincipal(Authentication authentication) {
        return authentication != null && authentication.getPrincipal() != null;
    }

    public static String getLoginId() {
        Authentication authentication = getAuthentication();
        if (authentication != null) return authentication.getName();

        return "";
    }

    public static Optional<String> getCurrentUsername() {
        final Authentication authentication = getCurrentUser();

        if (authentication == null) {
            log.debug("Security Context에 인증 정보가 없습니다.");
            return Optional.empty();
        }

        String username = null;
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
            username = springSecurityUser.getUsername();
        } else if (authentication.getPrincipal() instanceof String) {
            username = (String) authentication.getPrincipal();
        }

        return Optional.ofNullable(username);
    }

    public static Authentication getCurrentUser() {
        Optional<SecurityContext> securityContext = getSecurityContext();
        return securityContext.isEmpty() ? null : securityContext.get().getAuthentication();
    }

    public static Optional<SecurityContext> getSecurityContext() {
        return Optional.ofNullable(SecurityContextHolder.getContext());
    }

    public static boolean validateEmptyForUsernameAndPw(String username, String password) {
        return StringUtils.hasText(username) && StringUtils.hasText(password);
    }


}
