package com.wearedevs.config.security;

import com.wearedevs.config.security.dsl.AjaxLoginConfigurer;
import com.wearedevs.filter.AjaxLoginProcessingFilter;
import com.wearedevs.handler.security.AjaxAccessDeniedHandler;
import com.wearedevs.handler.security.AjaxAuthenticationFailureHandler;
import com.wearedevs.handler.security.AjaxAuthenticationSuccessHandler;
import com.wearedevs.provider.AjaxAuthenticationProvider;
import com.wearedevs.security.common.AjaxAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Order(0)
@Configuration
public class AjaxSecurityConfig extends WebSecurityConfigurerAdapter {
    private final AjaxAuthenticationProvider ajaxAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(
                        getAnyMatchersForHttpSecurity()
                ).permitAll()
                .anyRequest().authenticated();
                // ajax 인증 filter 추가 - 실제 추가하고자 하는 필터가 기존 필터 앞에 위치할 때, (UsernamePasswordAuthenticationFilter 필터 앞에 위치 시킨다.)
                //.and().addFilterBefore(ajaxLoginProcessingFilter(), UsernamePasswordAuthenticationFilter.class); // DSL 방식 대체

        http
                .exceptionHandling()
                .authenticationEntryPoint(new AjaxAuthenticationEntryPoint())
                .accessDeniedHandler(ajaxAccessDeniedHandler());

        http.csrf().disable();
        // DSL 방식
        customConfigurerAjax(http);
    }

    // DSL 방식
    private void customConfigurerAjax(HttpSecurity http) throws Exception {
        http
                .apply(new AjaxLoginConfigurer<>())
                .successHandlerAjax(ajaxAuthenticationSuccessHandler())
                .failureHandlerAjax(ajaxAuthenticationFailureHandler())
                .setAuthenticationManager(authenticationManagerBean())
                .loginProcessingUrl(AjaxLoginProcessingFilter.AJAX_LOGIN_PROCESSING_URL);
    }

    private String[] getAnyMatchersForHttpSecurity() {
        return new String[] {
                AjaxLoginProcessingFilter.AJAX_LOGIN_PROCESSING_URL
        };
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(ajaxAuthenticationProvider);
    }


    // DSL 방식으로 설정 대체 가능 (아래 방식을 대체 할 수 있는 설정)
    /*public AjaxLoginProcessingFilter ajaxLoginProcessingFilter() throws Exception {
        AjaxLoginProcessingFilter ajaxLoginProcessingFilter = new AjaxLoginProcessingFilter();
         ajaxLoginProcessingFilter.setAuthenticationManager(authenticationManagerBean());
        ajaxLoginProcessingFilter.setAuthenticationFailureHandler(ajaxAuthenticationFailureHandler());
        ajaxLoginProcessingFilter.setAuthenticationSuccessHandler(ajaxAuthenticationSuccessHandler());
        return ajaxLoginProcessingFilter;
    }*/

    @Bean
    public AuthenticationFailureHandler ajaxAuthenticationFailureHandler() {
        return new AjaxAuthenticationFailureHandler();
    }

    @Bean
    public AuthenticationSuccessHandler ajaxAuthenticationSuccessHandler() {
        return new AjaxAuthenticationSuccessHandler();
    }

    @Bean
    public AccessDeniedHandler ajaxAccessDeniedHandler() {
        return new AjaxAccessDeniedHandler();
    }



}
