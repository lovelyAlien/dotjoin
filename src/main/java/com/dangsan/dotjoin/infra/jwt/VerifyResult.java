package com.dangsan.dotjoin.infra.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VerifyResult {

    private String subject;
    private Collection<? extends GrantedAuthority> authorities;
    private String token;
    private boolean result;

//    public List<String> getAuthorityList(){
//        if(authorities.length()>0){
//            return Arrays.asList(authorities.split(","));
//        }
//        return new ArrayList<>();
//    }
//
//    public List<SimpleGrantedAuthority> getAuth(List<String> authorityList) {
//        return authorityList.stream()
//                .map(authority-> new SimpleGrantedAuthority(authority))
//                .collect(Collectors.toList());
//    }



}
