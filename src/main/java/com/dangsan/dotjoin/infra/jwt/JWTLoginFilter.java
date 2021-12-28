package com.dangsan.dotjoin.infra.jwt;


import com.dangsan.dotjoin.modules.account.dto.LoginDto;
import com.dangsan.dotjoin.modules.account.model.Account;
import com.dangsan.dotjoin.modules.account.model.UserAccount;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper;
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public JWTLoginFilter(ObjectMapper objectMapper, JWTUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.objectMapper = objectMapper;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;

        setFilterProcessesUrl("/login");

    }


    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        LoginDto loginDto = objectMapper.readValue(request.getInputStream(), LoginDto.class);
        System.out.println(loginDto);
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


        System.out.println("authResult.getPrincipal(): "+authResult.getPrincipal());
        System.out.println("authResult.getCredentials(): "+ authResult.getCredentials());
        System.out.println("authResult.toString(): "+ authResult.toString());
        System.out.println("authResult.getDetails(): "+ authResult.getDetails());



        response.addHeader(JWTUtil.AUTH_HEADER, JWTUtil.BEARER + jwtUtil.generate(authResult));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {

        System.out.println(failed.getMessage());

        super.unsuccessfulAuthentication(request, response, failed);
    }
}
