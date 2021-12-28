package com.dangsan.dotjoin.infra.jwt;

import com.dangsan.dotjoin.modules.account.dto.LoginDto;
import com.dangsan.dotjoin.modules.account.dto.SignUpDto;
import com.dangsan.dotjoin.modules.account.service.AccountService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.net.URI;
import java.net.URISyntaxException;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JWTLoginFilterTest {

    @Autowired
    AccountService accountService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private URI uri(String path) throws URISyntaxException {
        return new URI(format("http://localhost:%d%s", port, path));
    }


    @BeforeEach
    void before(){

        accountService.clearAllAccount();
        SignUpDto signUpForm= SignUpDto.builder()
                .nickname("qwer")
                .email("qwer1234@test.com")
                .password("qwer1234")
                .build();

        accountService.processNewAccount(signUpForm);


    }


    @DisplayName("1. jwt로 로그인을 시도한다.")
    @Test
    void test_1() throws URISyntaxException {
        LoginDto loginDto= LoginDto.builder().email("qwer1234@test.com")
                .password("qwer1234")
                .build();

        HttpEntity<LoginDto> body= new HttpEntity<>(loginDto);
        ResponseEntity<String> response= restTemplate.exchange(uri("/login"), HttpMethod.POST,body, String.class);
        assertEquals(200, response.getStatusCodeValue());

        System.out.println(response.getHeaders().get("Authentication"));




    }

}