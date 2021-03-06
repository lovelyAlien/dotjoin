package com.dangsan.dotjoin.infra.jwt;

import com.dangsan.dotjoin.modules.account.model.Account;
import com.dangsan.dotjoin.modules.account.model.UserAccount;
import com.dangsan.dotjoin.modules.account.service.AccountService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
public class JWTCheckFilter extends BasicAuthenticationFilter {
    private final AccountService accountService;
    private final JWTUtil jwtUtil;


    public JWTCheckFilter(AuthenticationManager authenticationManager, AccountService accountService, JWTUtil jwtUtil){
        super(authenticationManager);
        this.accountService=accountService;
        this.jwtUtil=jwtUtil;
    }

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {


        String requestURI = request.getRequestURI();

        String token= request.getHeader(JWTUtil.AUTHORIZATION_HEADER);
        if(token==null || !token.startsWith(JWTUtil.BEARER)){
            chain.doFilter(request, response);
            return;
        }

        VerifyResult result=jwtUtil.verify(token.substring(JWTUtil.BEARER.length()));


        log.info("VerifyResult result: {}", result);



        if(result.isResult()){

            Account account = accountService.findAccountByEmail(result.getEmail()).get();

            if(account==null){
                throw new UsernameNotFoundException("??? ??? ?????? ?????????: "+ result.getEmail());
            }

            UserAccount user=new UserAccount(account);

            Authentication auth = jwtUtil.getAuthentication(user);

            log.info("Authentication auth: {}", auth);

            log.info("auth.getAuthorities(): {}", auth.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(auth);

            log.info("Security Context??? '{}' ?????? ????????? ??????????????????, uri: {}", auth.getName(), requestURI);
        }
        else {
            log.info("????????? JWT ????????? ????????????, uri: {}", requestURI);
        }


        super.doFilterInternal(request, response, chain);
    }
}
