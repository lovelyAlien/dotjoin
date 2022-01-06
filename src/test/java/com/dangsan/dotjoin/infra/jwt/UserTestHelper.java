package com.dangsan.dotjoin.infra.jwt;

import com.dangsan.dotjoin.modules.account.model.Account;
import com.dangsan.dotjoin.modules.account.repository.AccountRepository;
import com.dangsan.dotjoin.modules.account.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@AllArgsConstructor
public class UserTestHelper {

    private final AccountService userService;

    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    public Account createUser(String nickname) throws DuplicateKeyException {
        Account account = Account.builder()
                .nickname(nickname)
                .email(nickname+"1234@test.com")
                .password(passwordEncoder.encode(nickname+"1234"))
                .build();
        return accountRepository.save(account);
    }

    public Account createUser(String nickname, String... roles){
        Account account = createUser(nickname);



        Stream.of(roles).forEach(role-> userService.addRole(account.getEmail(), role));

        return account;
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
