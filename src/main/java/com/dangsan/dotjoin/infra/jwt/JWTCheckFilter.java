package com.dangsan.dotjoin.infra.jwt;

import com.dangsan.dotjoin.modules.account.model.Account;
import com.dangsan.dotjoin.modules.account.model.UserAccount;
import com.dangsan.dotjoin.modules.account.service.AccountService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTCheckFilter extends BasicAuthenticationFilter {
    private final AccountService accountService;

    private final JWTUtil jwtUtil;


    public JWTCheckFilter(AuthenticationManager authenticationManager, AccountService accountService, JWTUtil jwtUtil){
        super(authenticationManager);
        this.accountService=accountService;
        this.jwtUtil=jwtUtil;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {


        String token= request.getHeader("Authentication");
        if(token==null || !token.startsWith("Bearer ")){
            chain.doFilter(request, response);
            return;
        }

        VerifyResult result=jwtUtil.verify(token.substring("Bearer ".length()));
        if(result.isResult()){
            Account account=accountService.findAccount(result.getUserId()).get();
            UserAccount userAccount=new UserAccount(account);

            Authentication auth= new UsernamePasswordAuthenticationToken(userAccount,
                    null,
                    userAccount.getAuthorities());


            SecurityContextHolder.getContext().setAuthentication(auth);
        }


        super.doFilterInternal(request, response, chain);
    }
}
