package com.wearedevs.config.security;

import com.wearedevs.api.resource.service.ResourceService;
import com.wearedevs.api.user.service.UserService;
import com.wearedevs.filter.PermitAllFilter;
import com.wearedevs.handler.security.FormAuthenticationFailureHandler;
import com.wearedevs.handler.security.FormAuthenticationSuccessHandler;
import com.wearedevs.provider.CustomAuthProvider;
import com.wearedevs.provider.TokenProvider;
import com.wearedevs.security.common.JwtAuthenticationEntryPoint;
import com.wearedevs.security.factory.UrlResourcesMapFactoryBean;
import com.wearedevs.security.metadatasource.UrlFilterInvocationSecurityMetadataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleHierarchyVoter;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.session.SessionFixationProtectionStrategy;

import java.util.ArrayList;
import java.util.List;

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
    private AccessDeniedHandler customFormAccessDeniedHandler;

    // JWT
    private final TokenProvider tokenProvider;
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    //private JwtAccessDeniedHandler jwtAccessDeniedHandler;
    //private final OAuth2AuthenticationHandler OAuth2AuthenticationHandler;
    // OAuth2
    //private final CustomOAuth2UserService customOAuth2UserService;
    private final ResourceService resourceService;

    //private String[] permitAllResources = {"/", "/api/login"};


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
                /*.
                customFilterSecurityInterceptor에서 permitAllFilter를 통해 다 처리 한다.
                antMatchers(
                        getAnyMatchersForHttpSecurity()
                ).permitAll()*/
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
                    .successHandler(formAuthenticationSuccessHandler())
                    .failureHandler(formAuthenticationFailureHandler())
                    .permitAll();

        //http.addFilterBefore(customFilterSecurityInterceptor(), FilterSecurityInterceptor.class); // 기존 Filter 앞에 custom Filter가 위치하도록 (custom이 먼저 실행된다.)

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

    @Bean
    public SessionFixationProtectionStrategy springSecuritySession() {
        return new SessionFixationProtectionStrategy();
    }

    @Bean
    public AuthenticationSuccessHandler formAuthenticationSuccessHandler() {
        return new FormAuthenticationSuccessHandler();
    }

    @Bean
    public AuthenticationFailureHandler formAuthenticationFailureHandler() {
        return new FormAuthenticationFailureHandler();
    }

    @Bean
    public PermitAllFilter customFilterSecurityInterceptor() throws Exception {
        // 인가 처리를 커스텀 방식 사용하도록 설정
        //FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
        PermitAllFilter permitAllFilter = new PermitAllFilter(getAnyMatchersForHttpSecurity()); // PermitAllFilter 추가
        permitAllFilter.setSecurityMetadataSource(urlFilterInvocationSecurityMetadataSource());
        // affirmativeBased (보편적, 많이 사용), consensusBased, unanimousBased
        permitAllFilter.setAccessDecisionManager(affirmativeBased());
        permitAllFilter.setAuthenticationManager(authenticationManagerBean()); // 인가 처리 전 인증된 사용자인지 검증이 필요
        return permitAllFilter;
    }

    private AccessDecisionManager affirmativeBased() {
        AffirmativeBased affirmativeBased = new AffirmativeBased(getAccessDecisionVoters());
        return affirmativeBased;
    }

    private List<AccessDecisionVoter<?>> getAccessDecisionVoters() {
        List<AccessDecisionVoter<? extends Object>> accessDecisionVoterList = new ArrayList<>();
        accessDecisionVoterList.add(roleVoter());
        //return Arrays.asList(new RoleVoter()); // TODO::: 권한 계층 조건 여기서 주면 된다. ex) Manager가 User 권한을 포함한다던가... (SpringInitializer.java에서 권한 계층 정보 세팅)
        return accessDecisionVoterList;
    }

    @Bean
    public AccessDecisionVoter<? extends Object> roleVoter() {
        RoleHierarchyVoter roleHierarchyVoter = new RoleHierarchyVoter(roleHierarchy());
        return roleHierarchyVoter;
    }

    @Bean
    public RoleHierarchyImpl roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        return roleHierarchy;
    }

    @Bean
    public FilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource() throws Exception {
        return new UrlFilterInvocationSecurityMetadataSource(urlResourcesMapFactoryBean().getObject(), resourceService);
    }

    private UrlResourcesMapFactoryBean urlResourcesMapFactoryBean() {
        UrlResourcesMapFactoryBean urlResourcesMapFactoryBean = new UrlResourcesMapFactoryBean(resourceService);
        //urlResourcesMapFactoryBean.setResourceService(resourceService);
        return urlResourcesMapFactoryBean;
    }

}
