package com.dangsan.dotjoin.infra.jwt;

import com.dangsan.dotjoin.modules.account.dto.LoginDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegrationTest {

    @LocalServerPort
    protected int port;

    @Autowired
    protected ObjectMapper objectMapper;

    protected RestTemplate restTemplate = new RestTemplate();


    protected URI uri(String path) throws URISyntaxException {
        return new URI(format("http://localhost:%d%s", port, path));
    }

    protected URI uri(String path, String... args) throws URISyntaxException {
        return uri(format(path, args));
    }


    protected Tokens getToken(String email, String password) throws URISyntaxException {
        LoginDto loginDto = LoginDto.builder().email(email)
                .type(LoginDto.Type.login)
                .password(password)
                .build();

        HttpEntity<LoginDto> body = new HttpEntity<>(loginDto);
        ResponseEntity<String> response = restTemplate.exchange(uri("/api/auth/login"), HttpMethod.POST, body, String.class);
        assertEquals(200, response.getStatusCodeValue());

        return Tokens.builder().accessToken(getAccessToken(response)).build();
    }

    protected String getAccessToken(ResponseEntity<String> response) {
        return response.getHeaders().get(JWTUtil.AUTHORIZATION_HEADER).get(0)
                .substring(JWTUtil.BEARER.length());


    }

    protected HttpEntity getAuthHeaderEntity(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(JWTUtil.AUTHORIZATION_HEADER, JWTUtil.BEARER + accessToken);
        HttpEntity entity = new HttpEntity("", headers);
        return entity;
    }

    protected HttpEntity getPostAuthHeaderEntity(String accessToken, Object object) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(JWTUtil.AUTHORIZATION_HEADER, JWTUtil.BEARER+ accessToken);
        HttpEntity entity = new HttpEntity(object, headers);
        return entity;
    }

}
