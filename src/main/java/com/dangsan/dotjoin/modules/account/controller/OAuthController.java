package com.dangsan.dotjoin.modules.account.controller;

import com.dangsan.dotjoin.modules.account.service.SecuredService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/oauth2")
public class OAuthController {


    @Autowired
    SecuredService securedService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/info")
    private String getOAuthSecuredInfo(@AuthenticationPrincipal OAuth2User user){
        return securedService.getGooglesecuredInfo();
    }



    @PreAuthorize("isAuthenticated()")
    @GetMapping("/user")
    public OAuth2User getOAuth2User(@AuthenticationPrincipal OAuth2User user) {

        return user;
    }




}
