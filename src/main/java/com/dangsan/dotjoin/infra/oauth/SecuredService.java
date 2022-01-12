package com.dangsan.dotjoin.infra.oauth;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class SecuredService {

    @PreAuthorize("hasAnyAuthority('ROLE_GOOGLE_USER')")
    public String getSecuredInfo(){
        return "secured info: google-secret";
    }
}
