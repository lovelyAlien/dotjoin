package com.dangsan.dotjoin.infra.jwt;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix="sp.jwt")
public class JWTProperties {
    private String secret = "default-secret-value";
    private long tokenLifeTime = 24*60*60; //600
    private long tokenRefreshTime = 24*60*60; // 86400
}
