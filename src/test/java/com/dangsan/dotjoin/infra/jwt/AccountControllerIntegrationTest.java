package com.dangsan.dotjoin.infra.jwt;

import com.dangsan.dotjoin.modules.account.dto.LoginDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private URI uri(String path) throws URISyntaxException {
        return new URI(format("http://localhost:%d%s", port, path));
    }

    String getToken(String email, String password) throws URISyntaxException {
        LoginDto loginDto= LoginDto.builder().email(email)
                .password(password)
                .build();

        HttpEntity<LoginDto> body= new HttpEntity<>(loginDto);
        ResponseEntity<String> response= restTemplate.exchange(uri("/login"), HttpMethod.POST,body, String.class);
        assertEquals(200, response.getStatusCodeValue());
        return response.getHeaders().get("Authentication").get(0).substring("Bearer ".length());


    }


    @BeforeEach
    void before(){
        prepareUserAdmin();
    }

    @DisplayName("1-1. user1 은 자신의 정보를 조회할 수 있다.")
    @Test
    void test_1_1() throws URISyntaxException {
        String accessToken = getToken("user1@test.com", "user1123").getAccessToken();
        ResponseEntity<User> response = restTemplate.exchange(uri("/user/"+USER1.getUserId()),
                HttpMethod.GET, getAuthHeaderEntity(accessToken), User.class);

        assertEquals(200, response.getStatusCodeValue());
        userTestHelper.assertUser(response.getBody(), "user1");
    }

    @DisplayName("1. admin 유저는 userList 를 가져올 수 있다.")
    @Test
    void test_1() throws URISyntaxException, JsonProcessingException {
        String accessToken = getToken("admin@test.com", "admin123").getAccessToken();

        ResponseEntity<String> response = restTemplate.exchange(uri("/user/list"),
                HttpMethod.GET, getAuthHeaderEntity(accessToken), String.class);

        RestResponsePage<User> page = objectMapper.readValue(response.getBody(),
                new TypeReference<RestResponsePage<User>>() {
                });

        assertEquals(2, page.getTotalElements());
        assertTrue(page.getContent().stream().map(user->user.getName())
                .collect(Collectors.toSet()).containsAll(Set.of("user1", "admin")));

        page.getContent().forEach(System.out::println);
    }




}
