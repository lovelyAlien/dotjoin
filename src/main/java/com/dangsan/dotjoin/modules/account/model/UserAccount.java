package com.dangsan.dotjoin.modules.account.model;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Slf4j
public class UserAccount extends User {

    private Long  id;
    private String nickname;


    public UserAccount(Account account) {

        super(account.getEmail(), account.getPassword(), getAuthorities(account.getRoleList()));

//        super(account.getEmail(), account.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
        this.id = account.getId();
        this.nickname=account.getNickname();
    }

    private static List<SimpleGrantedAuthority> getAuthorities(List<String> roles) {
        return roles.stream()
                .map(r-> new SimpleGrantedAuthority("ROLE_"+r))
                .collect(Collectors.toList());
    }

}
