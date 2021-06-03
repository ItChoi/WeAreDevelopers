package com.wearedevs.config;

import com.wearedevs.common.enumeration.user.UserAuthority;
import com.wearedevs.common.exception.jwt.CustomOAuth2AuthenticationHandler;
import com.wearedevs.common.exception.jwt.JwtAccessDeniedHandler;
import com.wearedevs.common.exception.jwt.JwtAuthenticationEntryPoint;
import com.wearedevs.common.utils.jwt.TokenProvider;
import com.wearedevs.web.oauth.service.CustomOAuth2UserService;
import com.wearedevs.web.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

// prePostEnabled를 메소드 단위로 추가하기 위하여 추가
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
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
                "/css/**",
                "/js/**",
                "/img/**",
                "/h2-console/**",
                "favicon.ico"
        );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                .antMatchers(
                        "/api/login", "/api/authenticate",
                        "/front/user/login"
                ).permitAll()
                /*.antMatchers(
                        "/api/user/**"
                ).hasAnyRole(
                        UserAuthority.SUPERVISOR.getCode()
                )*/

                .and().csrf()
                    .disable()

                .exceptionHandling()
                    .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                    .accessDeniedHandler(jwtAccessDeniedHandler)

                // H2 console을 위한 설정
                .and().headers()
                    .frameOptions()
                    .sameOrigin()

                // 세션 사용 안하기 때문에 STATELESS로 설정
                .and().sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                /*
                .and().formLogin()
                .loginPage("/front/user/login")
                // 커스텀 filter를 추가
                .and().apply(new JwtSecurityConfig(tokenProvider))

                // OAuth2
                .and().oauth2Login()
                    .userInfoEndpoint()
                    .userService(customOAuth2UserService)
                .and().successHandler(customOAuth2AuthenticationHandler)*/;




    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }
}
