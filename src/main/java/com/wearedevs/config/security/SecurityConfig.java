package com.wearedevs.config.security;

import com.wearedevs.api.user.service.UserService;
import com.wearedevs.common.exception.jwt.CustomOAuth2AuthenticationHandler;
import com.wearedevs.common.exception.jwt.JwtAccessDeniedHandler;
import com.wearedevs.common.exception.jwt.JwtAuthenticationEntryPoint;
import com.wearedevs.provider.CustomAuthProvider;
import com.wearedevs.provider.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.session.SessionFixationProtectionStrategy;

// prePostEnabled를 메소드 단위로 추가하기 위하여 추가
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
//@EnableWebSecurity(debug = true)
@EnableWebSecurity
@Order(1)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final CustomAuthProvider customProvider;
    private final AuthenticationDetailsSource authenticationDetailsSource;
    private final AuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final AuthenticationFailureHandler customAuthenticationFailureHandler;
    private AccessDeniedHandler customFormAccessDeniedHandler;

    // JWT
    private final TokenProvider tokenProvider;
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final CustomOAuth2AuthenticationHandler customOAuth2AuthenticationHandler;
    // OAuth2
    //private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 정적 파일들은 보안 필터를 거치지 않고 통과되도록 설정 (permitAll은 보안 필터는 거친다) (비용적인 면에서 더 좋다.)
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
        web.ignoring().antMatchers(
                getAnyMatchersForWebSecurity()
        );
    }
    private String[] getAnyMatchersForWebSecurity() {
        return new String[] {
                "/css/**",
                "/js/**",
                "/img/**",
                "/h2-console/**",
                "favicon.ico"
        };
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                .antMatchers(
                        getAnyMatchersForHttpSecurity()
                ).permitAll()
                .anyRequest().authenticated()
                /*.and().csrf()
                    .disable()*/

                // ajax 인증 filter 추가 - 실제 추가하고자 하는 필터가 기존 필터 앞에 위치할 때, (UsernamePasswordAuthenticationFilter 필터 앞에 위치 시킨다.)
                //.and().addFilterBefore(new AjaxLoginProcessingFilter(), UsernamePasswordAuthenticationFilter.class)

                .and().exceptionHandling()
                    .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                    //.accessDeniedHandler(jwtAccessDeniedHandler) // jwt
                    .accessDeniedHandler(customFormAccessDeniedHandler)


                // H2 console을 위한 설정
                .and().headers()
                    .frameOptions()
                    .sameOrigin()


                // FormLogin - spring security
                .and()
                    .formLogin()
                    //.loginPage("/login")
                    //.loginProcessingUrl("login_proc")
                    .authenticationDetailsSource(authenticationDetailsSource)
                    .defaultSuccessUrl("/")
                    .successHandler(customAuthenticationSuccessHandler)
                    .failureHandler(customAuthenticationFailureHandler)
                    .permitAll();
        http.csrf().disable();




                // 세션 사용 안하기 때문에 STATELESS로 설정 - usernameToken & JWT 방식 같이 사용해보기.
                /*.and().sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)*/
                /*.and().sessionManagement()
                .sessionAuthenticationStrategy(springSecuritySession())*/

                // 커스텀 filter를 추가
                //.and().apply(new JwtSecurityConfig(tokenProvider));

                // OAuth2
                /*.and().oauth2Login()
                    .userInfoEndpoint()
                    .userService(customOAuth2UserService)
                .and().successHandler(customOAuth2AuthenticationHandler);*/
    }

    @Bean
    public SessionFixationProtectionStrategy springSecuritySession() {
        return new SessionFixationProtectionStrategy();
    }

    private String[] getAnyMatchersForHttpSecurity() {
        return new String[] {
                "/api/login",
                "/api/login/**",
                "/api/authenticate",
                "/oauth2/**"
        };
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        String password = passwordEncoder.encode("1111");
        // 인메모리 계정 추가
        auth.inMemoryAuthentication().withUser("user").password(password).roles("USER");
        auth.inMemoryAuthentication().withUser("manager").password("{noop}1111").roles("MANAGER");
        auth.inMemoryAuthentication().withUser("admin").password(password).roles("ADMIN");
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder)
                .and().authenticationProvider(customProvider);
    }

}
