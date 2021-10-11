package com.dangsan.dotjoin.modules.account.model;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UserAccount extends User {

    private Long  id;
    private String nickname;


    public UserAccount(Account account) {
        super(account.getEmail(), account.getPassword(), getRoles(account.getRoleList()));
//        super(account.getEmail(), account.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
        this.id = account.getId();
        this.nickname=account.getNickname();
    }

    private static List<SimpleGrantedAuthority> getRoles(List<String> roles) {
        return roles.stream()
                .map(r-> new SimpleGrantedAuthority("ROLE_"+r))
                .collect(Collectors.toList());
    }

}
