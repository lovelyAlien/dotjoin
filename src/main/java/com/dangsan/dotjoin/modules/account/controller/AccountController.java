package com.dangsan.dotjoin.modules.account.controller;


import com.dangsan.dotjoin.infra.utils.RestResponsePage;
import com.dangsan.dotjoin.modules.account.dto.SignUpDto;
import com.dangsan.dotjoin.modules.account.model.Account;
import com.dangsan.dotjoin.modules.account.model.UserAccount;
import com.dangsan.dotjoin.modules.account.service.AccountService;
import com.dangsan.dotjoin.modules.account.dto.LoginDto;
import com.dangsan.dotjoin.modules.account.dto.TokenDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class AccountController {
    private final AccountService accountService;
    //    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello");
    }

    @PostMapping("/test-redirect")
    public void testRedirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/api/users");
    }

    @GetMapping("/test/oauth/login")
    public @ResponseBody
    String testOAuthLogin(
            Authentication authentication) {
        System.out.println(authentication);
        System.out.println("/test/oauth/login================");
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
//        System.out.println("authentication "+ oAuth2User.getAttributes());
//        System.out.println("oauth: "+ oauth.getAttributes());
//        System.out.println("userDetails: "+ userDetails.getAttributes());
        return "OAuth 세션 정보 확인하기";
    }

    @GetMapping("/sign-up")
    public String signUpForm() {
        return "signUp";
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Account> signUpSubmit(@Valid @RequestBody SignUpDto signUpForm, Errors errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
        }

        Account account = accountService.processNewUser(signUpForm);
//        accountService.login(account);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/login")
    public String loginForm() {
        return "/login";
    }

//    @PostMapping("/login")
//    public ResponseEntity<TokenDto> authorize(@Valid @RequestBody LoginDto loginDto) {
//
//        System.out.println("loginDto 받음");
//        UsernamePasswordAuthenticationToken authenticationToken =
//                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());
//
//        Authentication authentication =  authenticationManagerBuilder.getObject().authenticate(authenticationToken);
////        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        String jwt = tokenProvider.createToken(authentication);
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
//
//        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
//    }


//    @GetMapping("/logout")
//    public void logout(HttpServletRequest request, HttpServletResponse response){
//        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
//    }


    @GetMapping("/info")
    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    public ResponseEntity<?> getMyUserInfo(@AuthenticationPrincipal User user) {

        log.info("컨트롤 접근");
        log.info("SecurityContextHolder.getContext().getAuthentication().getAuthorities(): {}",SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        log.info("SecurityContextHolder.getContext().getAuthentication().getName(): {}", SecurityContextHolder.getContext().getAuthentication().getName());
        log.info("user.getUsername(): {}, user.getAuthorities(): {}", user.getUsername(), user.getAuthorities());
        if(user!=null){
            log.info("user.getUsername(): {}", user.getUsername());

        }

        else{
            log.info("user is null");
            return new ResponseEntity<>("@AuthenticationPrincipal is not working", HttpStatus.OK);
        }


        return new ResponseEntity<>(user.getUsername(), HttpStatus.OK);
    }


    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> getAllUser(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {



        log.info("컨트롤 접근");

        log.info("SecurityContextHolder.getContext().getAuthentication().getName(): {}",SecurityContextHolder.getContext().getAuthentication().getName());

        return new ResponseEntity<>(accountService.getAllUser(), HttpStatus.OK);
    }



}
