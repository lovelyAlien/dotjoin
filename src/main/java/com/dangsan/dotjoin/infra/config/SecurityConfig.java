package com.dangsan.dotjoin.infra.config;

import com.dangsan.dotjoin.infra.jwt.*;
import com.dangsan.dotjoin.infra.oauth.CustomOAuth2UserService;
import com.dangsan.dotjoin.infra.oauth.CustomOidcUserService;
import com.dangsan.dotjoin.infra.oauth.OidcUserToSiteUserFilter;
import com.dangsan.dotjoin.modules.account.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    CommonOAuth2Provider provider;

    private final AccountService accountService;
    private final JWTUtil jwtUtil;
    private final ObjectMapper objectMapper;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomOidcUserService customOidcUserService;

    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

//    @Autowired
//    private OidcUserToSiteUserFilter oidcUserToSiteUserFilter;







    public SecurityConfig(AccountService accountService, ObjectMapper objectMapper, JWTUtil jwtUtil) {
        this.accountService = accountService;
        this.objectMapper = objectMapper;
        this.jwtUtil = jwtUtil;


    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        RefreshableJWTLoginFilter refreshableJWTLoginFilter = new RefreshableJWTLoginFilter(objectMapper, jwtUtil, accountService, authenticationManager());
        JWTCheckFilter jwtCheckFilter = new JWTCheckFilter(authenticationManager(), accountService, jwtUtil);

        http.
                csrf().disable()
                .formLogin(
                        config->{
                            config.loginPage("/api/users/login")
                                    .successForwardUrl("/")
                                    .failureForwardUrl("/login?error=true");
                        }
                )
                .addFilter(refreshableJWTLoginFilter)
                .addFilter(jwtCheckFilter)

                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);


//        http
//                .oauth2Login(oauth -> {
//                    oauth.userInfoEndpoint(userinfo -> {
//
//                        userinfo.oidcUserService(customOidcUserService);
//
//                    });
//                })
//                .addFilterAfter(oidcUserToSiteUserFilter, OAuth2LoginAuthenticationFilter.class);

//                .loginPage("/login"); //구글 로그인이 완료된 후 후처리가 필요함.
//                .userInfoEndpoint()
//                .userService(oAuth2UserService);

//        super.configure(http);
    }


}
