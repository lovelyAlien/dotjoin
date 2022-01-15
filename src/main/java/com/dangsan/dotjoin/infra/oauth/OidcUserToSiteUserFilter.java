package com.dangsan.dotjoin.infra.oauth;


import com.dangsan.dotjoin.infra.jwt.JWTUtil;
import com.dangsan.dotjoin.modules.account.model.Account;
import com.dangsan.dotjoin.modules.account.model.UserAccount;
import com.dangsan.dotjoin.modules.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;


//@Component
@RequiredArgsConstructor
public class OidcUserToSiteUserFilter implements Filter {

    private final AccountService accountService;
    private final JWTUtil jwtUtil;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof OAuth2AuthenticationToken) {
            OidcUser oidcUser = (OidcUser) ((OAuth2AuthenticationToken) auth).getPrincipal();

            Account account = accountService.findAccountByProviderId("google_" + oidcUser.getSubject())
                    .orElseGet(() ->
                            accountService.save(
                                    Account.builder()
                                            .providerId("google_" + oidcUser.getSubject())
                                            .provider("google")
                                            .email(oidcUser.getEmail())
                                            .name(oidcUser.getFullName())
                                            .profileImgUrl(oidcUser.getPicture())
                                            .password(bCryptPasswordEncoder.encode("google_password"))
                                            .roles("USER,GOOGLE_USER")
                                            .verify(true)
                                            .build())

                    );

            UserAccount user = new UserAccount(account);
            auth = jwtUtil.getAuthentication(user);
            SecurityContextHolder.getContext().setAuthentication(auth);

        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
