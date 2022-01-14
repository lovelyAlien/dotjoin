package com.dangsan.dotjoin.infra.jwt;


import com.auth0.jwt.exceptions.TokenExpiredException;
import com.dangsan.dotjoin.modules.account.dto.LoginDto;
import com.dangsan.dotjoin.modules.account.model.Account;
import com.dangsan.dotjoin.modules.account.model.UserAccount;
import com.dangsan.dotjoin.modules.account.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class RefreshableJWTLoginFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper;
    private final JWTUtil jwtUtil;
    private final AccountService accountService;
    private final AuthenticationManager authenticationManager;

    public RefreshableJWTLoginFilter(ObjectMapper objectMapper, JWTUtil jwtUtil, AccountService accountService, AuthenticationManager authenticationManager) {
        this.objectMapper = objectMapper;
        this.jwtUtil = jwtUtil;
        this.accountService=accountService;
        this.authenticationManager = authenticationManager;

        setFilterProcessesUrl("/api/auth/login");
    }



    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        LoginDto loginDto = objectMapper.readValue(request.getInputStream(), LoginDto.class);

        // id password login
        if(loginDto.getType().equals(LoginDto.Type.login)){
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    loginDto.getEmail(), loginDto.getPassword(), null
            );
            return authenticationManager.authenticate(authToken);
        }else if(loginDto.getType().equals(LoginDto.Type.refresh)){
            // refresh token
            if(loginDto.getRefreshToken()==null)
                throw new IllegalArgumentException("리프레쉬 토큰이 필요함. : "+loginDto.getRefreshToken());

            VerifyResult result = jwtUtil.verify(loginDto.getRefreshToken());
            if(result.isResult()){
                Account account = accountService.findAccountByEmail(result.getEmail()).get();

                if(account==null){
                    throw new UsernameNotFoundException("알 수 없는 사용자: "+ result.getEmail());
                }

                UserAccount user=new UserAccount(account);

                Authentication auth = jwtUtil.getAuthentication(user);

                return auth;
            }else{
                throw new TokenExpiredException("리프레쉬 토큰 만료");
            }
        }else{
            throw new IllegalArgumentException("알 수 없는 타입 : "+loginDto.getType());
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        log.info("authResult:{}", authResult);
        log.info("authResult.getPrincipal(): {}", authResult.getPrincipal());
        log.info("authResult.getAuthorities(): {}", authResult.getAuthorities());







        response.addHeader(JWTUtil.AUTHORIZATION_HEADER, JWTUtil.BEARER + jwtUtil.generate(authResult, JWTUtil.TokenType.access));
        response.addHeader(JWTUtil.REFRESH_HEADER, jwtUtil.generate(authResult, JWTUtil.TokenType.refresh));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {

        System.out.println(failed.getMessage());

        super.unsuccessfulAuthentication(request, response, failed);
    }
}
