package com.dangsan.dotjoin.infra.oauth;

import com.dangsan.dotjoin.infra.jwt.JwtFilter;
import com.dangsan.dotjoin.infra.jwt.TokenProvider;
import com.dangsan.dotjoin.modules.account.dto.LoginDto;
import com.dangsan.dotjoin.modules.account.dto.TokenDto;
import com.dangsan.dotjoin.modules.account.model.Account;
import com.dangsan.dotjoin.modules.account.repository.AccountRepository;
import com.dangsan.dotjoin.modules.account.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Service
@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {
    private final TokenProvider tokenProvider;
//    private final UserRequestMapper userRequestMapper;
    private final ObjectMapper objectMapper;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AccountRepository accountRepository;
    private final AccountService accountService;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();
//        LoginDto LoginDto = userRequestMapper.toDto(oAuth2User);


        // 최초 로그인이라면 회원가입 처리를 한다.
        String provider= oAuth2User.getAttribute("provider");
        String providerId=oAuth2User.getAttribute("id");
        String nickname=oAuth2User.getAttribute("name");
        String username=provider+"_"+providerId;
        String password=bCryptPasswordEncoder.encode("OAUTH_PASSWORD");
        String email=oAuth2User.getAttribute("email");
        String roles="USER";
        String profileImgUrl= oAuth2User.getAttribute("picture");
        Account accountEntity=accountRepository.findByEmail(email);


        if(accountEntity==null){
            System.out.println(provider+ " 로그인이 최초입니다.");
            accountEntity = Account.builder()
                    .email(email)
                    .nickname(nickname)
                    .password(password)
                    .roles(roles)
                    .username(username)
                    .profileImgUrl(profileImgUrl)
                    .provider(provider)
                    .providerId(providerId)
                    .build();
            accountRepository.save(accountEntity);
        }
        else{
            System.out.println(provider+ " 로그인을 이미 한 적 있습니다. 자동 회원가입이 되어 있습니다.");

        }

        log.info("authentication: {}", authentication);
        log.info("oAuth2User: {}", oAuth2User);


        String jwt = tokenProvider.createToken(authentication);

        log.info("jwt: {}", jwt);

        writeTokenResponse(response, new TokenDto(jwt));
    }

    private void writeTokenResponse(HttpServletResponse response, TokenDto tokenDto)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        response.addHeader("Authorization", "Bearer " + tokenDto.getToken());
//        response.addHeader("Refresh", token.getRefreshToken());
        response.setContentType("application/json;charset=UTF-8");

        var writer = response.getWriter();
        writer.println(objectMapper.writeValueAsString(tokenDto));
        writer.flush();
    }
}
