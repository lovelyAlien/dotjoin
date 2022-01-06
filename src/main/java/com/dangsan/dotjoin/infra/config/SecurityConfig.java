package com.dangsan.dotjoin.infra.config;

import com.dangsan.dotjoin.infra.jwt.JWTCheckFilter;
import com.dangsan.dotjoin.infra.jwt.JWTLoginFilter;
import com.dangsan.dotjoin.infra.jwt.JWTProperties;
import com.dangsan.dotjoin.infra.jwt.JWTUtil;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final AccountService accountService;
    private final JWTUtil jwtUtil;
    private final ObjectMapper objectMapper;
    private final JWTProperties jwtProperties;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public SecurityConfig(AccountService accountService, ObjectMapper objectMapper, JWTProperties jwtProperties){
        this.accountService=accountService;
        this.objectMapper=objectMapper;
        this.jwtProperties=jwtProperties;
        this.jwtUtil=new JWTUtil(jwtProperties);

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
        JWTLoginFilter jwtLoginFilter=new JWTLoginFilter(objectMapper, jwtUtil, authenticationManager());
        JWTCheckFilter jwtCheckFilter=new JWTCheckFilter(authenticationManager(), accountService, jwtUtil);
        http.csrf().disable()
                .formLogin(
                        config->{
                            config.loginPage("/login")
                                    .successForwardUrl("/")
                                    .failureForwardUrl("/login?error=true");
                        }
                )
                .addFilter(jwtLoginFilter)
                .addFilter(jwtCheckFilter)

                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

//        super.configure(http);
    }


}
