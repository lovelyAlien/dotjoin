package com.dangsan.dotjoin.infra.oauth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OAuth2Controller {

    private final SecuredService securedService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/oauth2/info")
    public String getOAuth2SecuredInfo(@AuthenticationPrincipal User user) {
        log.info("user.getAuthorities(): {}", user.getAuthorities());
        log.info("SecurityContextHolder.getContext().getAuthentication().getAuthorities(): {}",
                SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        return securedService.getSecuredInfo();

    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/oauth2/user")
    public User getOAuth2User(@AuthenticationPrincipal User user) {

        return user;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/site/user")
    public User getSiteUser(@AuthenticationPrincipal User user) {

        return user;
    }


}
