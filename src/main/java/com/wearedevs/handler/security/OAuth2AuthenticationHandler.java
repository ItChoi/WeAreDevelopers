package com.wearedevs.handler.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
//public class OAuth2AuthenticationHandler extends SavedRequestAwareAuthenticationSuccessHandler {
public class OAuth2AuthenticationHandler implements AuthenticationSuccessHandler {

    /*@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        System.out.println("test@@@@@@@@@@@@@@@@@@");
    }*/



    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("TEST OAuth2AuthenticationHandler - onAuthenticationSuccess");
    }
}
