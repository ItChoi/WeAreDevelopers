package com.wearedevs.config.security;

import com.wearedevs.common.exception.jwt.CustomOAuth2AuthenticationHandler;
import com.wearedevs.common.exception.jwt.JwtAccessDeniedHandler;
import com.wearedevs.common.exception.jwt.JwtAuthenticationEntryPoint;
import com.wearedevs.common.utils.jwt.TokenProvider;
import com.wearedevs.config.JwtSecurityConfig;
import com.wearedevs.config.security.provider.CustomAuthProvider;
import com.wearedevs.web.oauth.service.CustomOAuth2UserService;
import com.wearedevs.web.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.session.SessionFixationProtectionStrategy;

// prePostEnabled를 메소드 단위로 추가하기 위하여 추가
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
//@EnableWebSecurity(debug = true)
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final CustomAuthProvider customProvider;


    // JWT
    private final TokenProvider tokenProvider;
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final CustomOAuth2AuthenticationHandler customOAuth2AuthenticationHandler;
    // OAuth2
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                getAnyMatchersForWebSecurity()
                /*"/css/**",
                "/js/**",
                "/img/**",
                "/h2-console/**",
                "favicon.ico"*/
        );
    }
    private String[] getAnyMatchersForWebSecurity() {
        return new String[] {
                "/css/**",
                "/js/**",
                "/img/**",
                "/h2-console/**",
                "favicon.ico"};
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                .antMatchers(
                        getAnyMatchersForHttpSecurity()
                        /*"/api/login", "/api/authenticate",
                        "/front/user/login"*/
                ).permitAll()
                .anyRequest().authenticated()
                /*.antMatchers(
                        "/api/user/**"
                ).hasAnyRole(
                        UserAuthority.SUPERVISOR.getCode()
                )*/
                //.and().formLogin() // 디폴트 시큐리티 프로세스 분석용
                .and().csrf()
                    .disable()

                .exceptionHandling()
                    .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                    .accessDeniedHandler(jwtAccessDeniedHandler)

                // H2 console을 위한 설정
                .and().headers()
                    .frameOptions()
                    .sameOrigin()

                // 세션 사용 안하기 때문에 STATELESS로 설정 - usernameToken & JWT 방식 같이 사용해보기.
                /*.and().sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)*/
                .and().sessionManagement()
                .sessionAuthenticationStrategy(springSecuritySession())

                // 커스텀 filter를 추가
                .and().apply(new JwtSecurityConfig(tokenProvider))

                // OAuth2
                .and().oauth2Login()
                    .userInfoEndpoint()
                    .userService(customOAuth2UserService)
                .and().successHandler(customOAuth2AuthenticationHandler);
    }

    @Bean
    public SessionFixationProtectionStrategy springSecuritySession() {
        return new SessionFixationProtectionStrategy();
    }

    private String[] getAnyMatchersForHttpSecurity() {
        return new String[] {
                "/api/login",
                "/api/authenticate",
                "/front/user/login"
        };
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder)
                .and().authenticationProvider(customProvider);
    }

}
