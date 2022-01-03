package com.dangsan.dotjoin.infra.jwt;


import com.dangsan.dotjoin.modules.account.dto.LoginDto;
import com.dangsan.dotjoin.modules.account.model.Account;
import com.dangsan.dotjoin.modules.account.model.UserAccount;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper;
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public JWTLoginFilter(ObjectMapper objectMapper, JWTUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.objectMapper = objectMapper;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;

        setFilterProcessesUrl("/api/auth/login");
    }



    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        LoginDto loginDto = objectMapper.readValue(request.getInputStream(), LoginDto.class);
        log.info("loginDto: {}", loginDto);

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginDto.getEmail(),
                loginDto.getPassword(),
                null);
        return authenticationManager.authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {


        User user=(User)authResult.getPrincipal();
        log.info("user.toString(): {}", user.toString());




        log.info("authResult.toString(): {}", authResult.toString());
        log.info("authResult.getPrincipal(): {}", authResult.getPrincipal());
        log.info("authResult.getCredentials(): {}", authResult.getCredentials());
        log.info("authResult.getDetails(): {}", authResult.getDetails());



        response.addHeader(JWTUtil.AUTHORIZATION_HEADER, JWTUtil.BEARER + jwtUtil.generate(authResult));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {

        System.out.println(failed.getMessage());

        super.unsuccessfulAuthentication(request, response, failed);
    }
}
