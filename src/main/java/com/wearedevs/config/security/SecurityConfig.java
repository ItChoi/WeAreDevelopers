package com.wearedevs.config.security;

import com.wearedevs.config.security.provider.CustomAuthProvider;
import com.wearedevs.web.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ProviderManager;
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
    private final CustomAuthProvider customProvider;
//    private final AuthenticationProvider customProvider;
    //private final


    // JWT
    /*private final TokenProvider tokenProvider;
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final CustomOAuth2AuthenticationHandler customOAuth2AuthenticationHandler;*/
    // OAuth2
    //private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                //getAnyMatchersForWebSecurity()
                "/css/**",
                "/js/**",
                "/img/**",
                "/h2-console/**",
                "favicon.ico"
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
                        //getAnyMatchersForHttpSecurity()
                        "/api/login", "/api/authenticate",
                        "/front/user/login"
                ).permitAll()
                /*.antMatchers(
                        "/api/user/**"
                ).hasAnyRole(
                        UserAuthority.SUPERVISOR.getCode()
                )*/
                //.and().formLogin()
                .and().csrf()
                    .disable()

                /*.exceptionHandling()
                    .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                    .accessDeniedHandler(jwtAccessDeniedHandler)*/

                // H2 console을 위한 설정
                .headers()
                    .frameOptions()
                    .sameOrigin()

                // 세션 사용 안하기 때문에 STATELESS로 설정
                .and().sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                /*// 커스텀 filter를 추가
                .and().apply(new JwtSecurityConfig(tokenProvider))

                // OAuth2
                .and().oauth2Login()
                    .userInfoEndpoint()
                    .userService(customOAuth2UserService)
                .and().successHandler(customOAuth2AuthenticationHandler)*/;
    }

    private String[] getAnyMatchersForHttpSecurity() {
        return new String[] {
                "/api/login", "/api/authenticate",
                "/front/user/login"};
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder)
                .and().authenticationProvider(customProvider);
    }

}
