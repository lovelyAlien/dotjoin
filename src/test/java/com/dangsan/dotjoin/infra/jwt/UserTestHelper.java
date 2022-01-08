package com.dangsan.dotjoin.infra.jwt;

import com.dangsan.dotjoin.modules.account.model.Account;
import com.dangsan.dotjoin.modules.account.repository.AccountRepository;
import com.dangsan.dotjoin.modules.account.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@AllArgsConstructor
public class UserTestHelper {

    private final AccountService userService;

    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    public Account createUser(String nickname, String... roles){
        StringBuilder sb=new StringBuilder();

        for(String role: roles){
                if(sb.toString().equals(""))
                    sb.append(role);
                else{
                    sb.append(",");
                    sb.append(role);
                }
        }

        String roleList=sb.toString();
        Account account = Account.builder()
                .nickname(nickname)
                .email(nickname+"1234@test.com")
                .password(passwordEncoder.encode(nickname+"1234"))
                .roles(roleList)
                .build();
        return accountRepository.save(account);
    }

    public void assertUser(Account account, String nickname){
        assertNotNull(account.getId());
        assertEquals(nickname, account.getNickname());
        assertEquals(nickname+"1234@test.com", account.getEmail());
    }

    public void assertUser(Account account, String nickname,  String... roles){
        assertUser(account, nickname);
        assertTrue(account.getRoleList().containsAll(
                Stream.of(roles).collect(Collectors.toList())
        ));
    }

}
