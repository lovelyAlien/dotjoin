package com.dangsan.dotjoin.infra.jwt;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.HttpClientErrorException;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RefreshTokenTest extends RefreshableTwoUserIntegrationTest{
    @Autowired
    JWTUtil jwtUtil;

    @BeforeEach
    void before() throws URISyntaxException {
        prepareTwoUsers();

        Tokens tokens= getToken("user11234@test.com", "user11234");
        assertNotNull(tokens.getAccessToken());
        assertNotNull(tokens.getRefreshToken());



    }
    @DisplayName("1. user2 가 게시물을 조회하고, 일정 시간이 지나 토큰이 만료된 후 다시 조회한다.")
    @Test
    void test_1() throws URISyntaxException, InterruptedException {
        jwtUtil.getProperties().setTokenLifeTime(1);

        Tokens tokens= getToken("user21234@test.com", "user21234");
        ResponseEntity<String> response=restTemplate.exchange(uri("/api/users/info"),
                HttpMethod.GET, getAuthHeaderEntity(tokens.getAccessToken()),String.class);

        assertEquals(200, response.getStatusCodeValue());

        Thread.sleep(2000);

        assertThrows(HttpClientErrorException.class, ()->{
            restTemplate.exchange(uri("/api/users/info"),
                    HttpMethod.GET, getAuthHeaderEntity(tokens.getAccessToken()), String.class);

        });

        Tokens refreshedTokens= getRefreshToken(tokens.getRefreshToken());

        response=restTemplate.exchange(uri("/api/users/info"),
                HttpMethod.GET, getAuthHeaderEntity(refreshedTokens.getAccessToken()),String.class);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("user21234@test.com", response.getBody());




    }
}
