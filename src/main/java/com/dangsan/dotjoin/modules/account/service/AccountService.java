package com.dangsan.dotjoin.modules.account.service;

import com.dangsan.dotjoin.infra.utils.SecurityUtil;
import com.dangsan.dotjoin.modules.account.dto.SignUpDto;
import com.dangsan.dotjoin.modules.account.model.Account;
import com.dangsan.dotjoin.modules.account.model.UserAccount;
import com.dangsan.dotjoin.modules.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.sql.Update;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String emailOrNickname) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(emailOrNickname).get();
        if (account == null) {
            account = accountRepository.findByNickname(emailOrNickname);
        }

        if (account == null) {
            throw new UsernameNotFoundException(emailOrNickname);
        }

        return new UserAccount(account);
    }


    public Account processNewUser(SignUpDto signUpForm) {
        if (accountRepository.existsByEmail(signUpForm.getEmail()) || accountRepository.existsByNickname(signUpForm.getNickname())) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }
        Account newAccount = saveNewAccount(signUpForm, "USER");
//        sendSignUpConfirmEmail(newAccount);
        return newAccount;
    }


    public Account processNewAdmin(SignUpDto signUpForm) {
        if (accountRepository.existsByEmail(signUpForm.getEmail()) || accountRepository.existsByNickname(signUpForm.getNickname())) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }
        Account newAccount = saveNewAccount(signUpForm, "ADMIN");
//        sendSignUpConfirmEmail(newAccount);
        return newAccount;
    }

    private Account saveNewAccount(@Valid SignUpDto signUpForm, String role) {

        signUpForm.setPassword(passwordEncoder.encode(signUpForm.getPassword()));
        Account account = modelMapper.map(signUpForm, Account.class);
        account.addRole(role);
        account.generateEmailCheckToken();
        return accountRepository.save(account);
    }


//    public void login(Account account) {
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
//                new UserAccount(account),
//                account.getPassword(),
//                List.of(new SimpleGrantedAuthority("ROLE_USER")));
//        SecurityContextHolder.getContext().setAuthentication(token);
//    }

//    @Transactional(readOnly = true)
//    public Optional<User> getUserWithAuthorities(String username) {
//        return userRepository.findOneWithAuthoritiesByUsername(username);
//    }
//


    public void clearAllAccount() {
        accountRepository.deleteAll();
    }


    public List<Account> getAllUser() {

        System.out.println("서비스 접근");

        return accountRepository.findAll();
    }

    public Optional<Account> findAccountByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    public Account findAccountByNickname(String nickname) {
        return accountRepository.findByNickname(nickname);
    }

    public Account save(Account account) {
        return accountRepository.save(account);
    }

    public Optional<Account> findAccountByProviderId(String providerId) {
        return accountRepository.findByProviderId(providerId);
    }

    public void addRole(String email, String role) {
        Account account = accountRepository.findByEmail(email).get();
        account.addRole(role);


    }

}
