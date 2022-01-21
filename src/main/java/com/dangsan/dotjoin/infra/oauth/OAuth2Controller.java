package com.dangsan.dotjoin.infra.oauth;

import com.dangsan.dotjoin.modules.account.model.UserAccount;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OAuth2Controller {


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/oauth2/user")
    public UserAccount getOAuth2User(@AuthenticationPrincipal UserAccount user) {

        return user;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/site/user")
    public UserAccount getSiteUser(@AuthenticationPrincipal  UserAccount user) {

        return user;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/user")
    public UserAccount getUser(@AuthenticationPrincipal  UserAccount user) {

        return user;
    }


}
