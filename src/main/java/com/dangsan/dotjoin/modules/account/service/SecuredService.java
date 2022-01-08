package com.dangsan.dotjoin.modules.account.service;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class SecuredService {

    @PreAuthorize("hasAnyAuthority('FROM_GOOGLE')")
    public String getGooglesecuredInfo(){
        return "secured info: google-secret";
    }
}
