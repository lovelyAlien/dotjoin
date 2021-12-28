package com.dangsan.dotjoin.infra.config;

import com.dangsan.dotjoin.infra.jwt.JWTLoginFilter;
import com.dangsan.dotjoin.infra.jwt.JWTUtil;
import com.dangsan.dotjoin.modules.account.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AccountService accountService;

    @Autowired
    JWTUtil jwtUtil;

    @Autowired
    ObjectMapper objectMapper;


    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        JWTLoginFilter jwtLoginFilter=new JWTLoginFilter(objectMapper, jwtUtil, authenticationManager());

        http.csrf().disable()
                .formLogin(
                        config->{
                            config.loginPage("/login")
                                    .successForwardUrl("/")
                                    .failureForwardUrl("/login?error=true");
                        }
                )
                .addFilter(jwtLoginFilter);
//        super.configure(http);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

}
