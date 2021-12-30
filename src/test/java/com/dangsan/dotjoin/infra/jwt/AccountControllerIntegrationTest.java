package com.dangsan.dotjoin.infra.jwt;

import com.dangsan.dotjoin.infra.utils.RestResponsePage;
import com.dangsan.dotjoin.modules.account.dto.LoginDto;
import com.dangsan.dotjoin.modules.account.dto.SignUpDto;
import com.dangsan.dotjoin.modules.account.model.Account;
import com.dangsan.dotjoin.modules.account.repository.AccountRepository;
import com.dangsan.dotjoin.modules.account.service.AccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountControllerIntegrationTest {
    @LocalServerPort
    private int port;


    @Autowired
    private AccountService accountService;


    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private URI uri(String path) throws URISyntaxException {
        return new URI(format("http://localhost:%d%s", port, path));
    }

    String getToken(String email, String password) throws URISyntaxException {
        LoginDto loginDto= LoginDto.builder().email(email)
                .password(password)
                .build();

        HttpEntity<LoginDto> body= new HttpEntity<>(loginDto);
        ResponseEntity<String> response= restTemplate.exchange(uri("/api/auth/login"), HttpMethod.POST,body, String.class);
        assertEquals(200, response.getStatusCodeValue());
        return response.getHeaders().get("Authentication").get(0).substring("Bearer ".length());


    }


    @BeforeEach
    void before(){

        accountService.clearAllAccount();
        SignUpDto user= SignUpDto.builder()
                .nickname("user")
                .email("user1234@test.com")
                .password("user1234")
                .build();

        accountService.processNewUser(user);

        SignUpDto admin= SignUpDto.builder()
                .nickname("admin")
                .email("admin1234@test.com")
                .password("admin1234")
                .build();

        accountService.processNewAdmin(admin);

    }

//    @DisplayName("1-1. user1 은 자신의 정보를 조회할 수 있다.")
//    @Test
//    void test_1_1() throws URISyntaxException {
//        String accessToken = getToken("qwer1234@test.com", "qwer1234");
//        ResponseEntity<User> response = restTemplate.exchange(uri("/user/"+USER1.getUserId()),
//                HttpMethod.GET, getAuthHeaderEntity(accessToken), User.class);
//
//        assertEquals(200, response.getStatusCodeValue());
//        userTestHelper.assertUser(response.getBody(), "user1");
//    }

    @DisplayName("1. admin 유저는 userList 를 가져올 수 있다.")
    @Test
    void test_1() throws URISyntaxException, JsonProcessingException {
        String accessToken = getToken("admin1234@test.com", "admin1234");


        HttpHeaders headers= new HttpHeaders();
        headers.add("Authentication", "Bearer "+accessToken);
        HttpEntity entity= new HttpEntity("", headers);
        ResponseEntity<String> response = restTemplate.exchange(uri("/api/users/list"),
                HttpMethod.GET, entity, String.class);

        RestResponsePage<Account> page = objectMapper.readValue(response.getBody(),
                new TypeReference<RestResponsePage<Account>>() {
                });

        assertEquals(2, page.getTotalElements());
        assertTrue(page.getContent().stream().map(user->user.getNickname())
                .collect(Collectors.toSet()).containsAll(Set.of("user", "admin")));

        page.getContent().forEach(System.out::println);
    }




}
