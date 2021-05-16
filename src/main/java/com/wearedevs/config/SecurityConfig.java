package com.wearedevs.config;

import com.wearedevs.common.exception.jwt.JwtAccessDeniedHandler;
import com.wearedevs.common.exception.jwt.JwtAuthenticationEntryPoint;
import com.wearedevs.common.utils.jwt.TokenProvider;
import com.wearedevs.web.user.service.UserService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;




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
                csrf()
                    .disable()

                .exceptionHandling()
                    .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                    .accessDeniedHandler(jwtAccessDeniedHandler)

                // H2 console을 위한 설정
                .and()
                    .headers()
                    .frameOptions()
                    .sameOrigin()

                // 세션 사용 안하기 때문에 STATELESS로 설정
                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and().authorizeRequests()
                    .antMatchers(
                            "/api/login", "/api/user"
                    ).permitAll()
                    .anyRequest().authenticated()

                // 커스텀 filter를 추가
                .and()
                    .apply(new JwtSecurityConfig(tokenProvider));


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }
}
