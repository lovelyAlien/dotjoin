package com.dangsan.dotjoin.modules.account.model;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Slf4j
public class UserAccount implements UserDetails {
    private static final long serialVersionUID = 1L;
    //    private Long  id;
//    private String nickname;
    private Account account;
    private Map<String, Object> attributes;

    // 일반 시큐리티 로그인시 사용
    public UserAccount(Account account){
        this.account=account;
    }

    // OAuth2.0 로그인시 사용
    public UserAccount(Account account, Map<String, Object> attributes) {
        this.account = account;
        this.attributes = attributes;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<String> roles=account.getRoleList();
        return roles.stream()
                .map(r->new SimpleGrantedAuthority("ROLE_"+ r))
                .collect(Collectors.toSet());

    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return account.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

//
//    public UserAccount(Account account) {
//
//        super(account.getEmail(), account.getPassword(), getAuthorities(account.getRoleList()));
//        this.id = account.getId();
//        this.nickname=account.getNickname();
//    }
//
//    private static List<SimpleGrantedAuthority> getAuthorities(List<String> roles) {
//        return roles.stream()
//                .map(r-> new SimpleGrantedAuthority("ROLE_"+r))
//                .collect(Collectors.toList());
//    }

}