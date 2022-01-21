package com.dangsan.dotjoin.infra.jwt;

import com.dangsan.dotjoin.modules.account.dto.LoginDto;
import com.dangsan.dotjoin.modules.account.model.Account;
import com.dangsan.dotjoin.modules.account.repository.AccountRepository;
import com.dangsan.dotjoin.modules.account.service.AccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RefreshableIntegrationTest extends  IntegrationTest{


    protected Tokens getToken(String email, String password) throws URISyntaxException {
        LoginDto loginDto = LoginDto.builder()
                .email(email)
                .type(LoginDto.Type.login)
                .password(password)
                .build();

        HttpEntity<LoginDto> body = new HttpEntity<>(loginDto);
        ResponseEntity<String> response = restTemplate.exchange(uri("/api/auth/login"), HttpMethod.POST, body, String.class);
        assertEquals(200, response.getStatusCodeValue());

        return Tokens.builder()
                .accessToken(getAccessToken(response))
                .refreshToken(getRefreshToken(response))
                .build();
    }

    protected Tokens getRefreshToken(String refreshToken) throws URISyntaxException {
        LoginDto loginDto = LoginDto.builder()
                .type(LoginDto.Type.refresh)
                .refreshToken(refreshToken)
                .build();

        HttpEntity<LoginDto> body = new HttpEntity<>(loginDto);
        ResponseEntity<String> response = restTemplate.exchange(uri("/api/auth/login"), HttpMethod.POST, body, String.class);
        assertEquals(200, response.getStatusCodeValue());

        return Tokens.builder()
                .accessToken(getAccessToken(response))
                .refreshToken(getRefreshToken(response))
                .build();
    }

    protected String getRefreshToken(ResponseEntity<String> response) {
        return response.getHeaders().get(JWTUtil.REFRESH_HEADER).get(0);


    }



}
