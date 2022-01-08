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
import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.spi.SessionFactoryDelegatingImpl;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class  AccountControllerIntegrationTest extends IntegrationTest{

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTUtil jwtUtil;

    private UserTestHelper userTestHelper;

    private Account USER;
    private Account ADMIN;

    @BeforeEach
    void before() {

        accountService.clearAllAccount();

        this.userTestHelper=new UserTestHelper(accountService, accountRepository, passwordEncoder);

        this.USER=this.userTestHelper.createUser("user", "USER");
        this.ADMIN=this.userTestHelper.createUser("admin", "ADMIN");




    }


    @DisplayName("1. admin 유저는 userList 를 가져올 수 있다.")
    @Test
    void test_1() throws URISyntaxException, JsonProcessingException {

        String accessToken = getToken("admin1234@test.com", "admin1234").getAccessToken();

        log.info("accessToken: {}", accessToken);

        ResponseEntity<String> response = restTemplate.exchange(uri("/api/users/list"),
                HttpMethod.GET, getAuthHeaderEntity (accessToken), String.class);

        log.info("response.getBody(): {}", response.getBody());

        List<Account> accounts= objectMapper.readValue(
                response.getBody(),
                new TypeReference<List<Account>>() {
        });


        assertEquals(2, accounts.size());

        Set<String> names= accounts.stream().map(account->account.getNickname()).collect(Collectors.toSet());
        assertTrue(names.containsAll(Set.of("user", "admin")));
    }



    @DisplayName("1. user 유저에게 admin 권한을 준다.")
    @Test
    void test_2() throws URISyntaxException, JsonProcessingException {

        accountService.addRole("user1234@test.com", "ADMIN");

        String accessToken = getToken("user1234@test.com", "user1234").getAccessToken();

        ResponseEntity<String> response = restTemplate.exchange(uri("/api/users/list"),
                HttpMethod.GET, getAuthHeaderEntity(accessToken), String.class);

        log.info("response.getBody(): {}", response.getBody());

        List<Account> accounts= objectMapper.readValue(
                response.getBody(),
                new TypeReference<List<Account>>() {
                });


        assertEquals(2, accounts.size());

        Set<String> names= accounts.stream().map(account->account.getNickname()).collect(Collectors.toSet());
        assertTrue(names.containsAll(Set.of("user", "admin")));
    }

}
