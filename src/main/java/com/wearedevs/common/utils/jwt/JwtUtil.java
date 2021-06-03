package com.wearedevs.common.utils.jwt;

import com.wearedevs.common.exception.jwt.JwtTokenNotFoundException;
import com.wearedevs.config.filter.JwtFilter;
import com.wearedevs.web.auth.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
public class JwtUtil {

}
