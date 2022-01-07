package com.dangsan.dotjoin.infra.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dangsan.dotjoin.modules.account.model.UserAccount;
import com.dangsan.dotjoin.modules.account.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JWTUtil {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER = "Bearer ";
    private static final String AUTHORITIES_KEY = "auth";
    public static final String REFRESH_HEADER = "refresh-token";
    private static final String EXPIRATION_TIME = "exp";

    private Algorithm AL;


    public static enum TokenType {
        access,
        refresh
    }

    private final JWTProperties properties;

    public JWTUtil(JWTProperties properties) {
        this.properties = properties;
        this.AL = Algorithm.HMAC512(properties.getSecret());
    }

    public String generate(Authentication authentication) {
        return generate(authentication, TokenType.access);
    }

    public String generate(Authentication authentication, TokenType type) {

//        String authorities = authentication.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.joining(","));


        log.info("authentication: {}", authentication);
        log.info("authentication.getPrincipal(): {}", authentication.getPrincipal());
        log.info("authentication.getName(): {}", authentication.getName());


        return JWT.create().withSubject(authentication.getName())
                .withClaim(EXPIRATION_TIME, Instant.now().getEpochSecond() + getLifeTime(type))
//                .withClaim(AUTHORITIES_KEY, authorities)
                .sign(AL);
    }

    private long getLifeTime(TokenType type) {
        switch (type) {
            case refresh:
                return this.properties.getTokenRefreshTime();
            case access:
            default:
                return this.properties.getTokenLifeTime();
        }
    }

    public VerifyResult verify(String token) {


        try {
            DecodedJWT decode = JWT.require(AL).build().verify(token);

            log.info("decode.getHeader(): {}, decode.getSubject(): {}, decode.getPayload(): {}, decode.getSignature(): {}, decode.getClaims(): {}",
                    decode.getHeader(), decode.getSubject(), decode.getPayload(), decode.getSignature(), decode.getClaims());

//            log.info("decode.getClaim(AUTHORITIES_KEY): {}", decode.getClaim(AUTHORITIES_KEY));
//
//            log.info("decode.getClaim(AUTHORITIES_KEY).toString(): {}", decode.getClaim(AUTHORITIES_KEY).toString());
//
//            log.info("decode.getClaim(AUTHORITIES_KEY).asString(): {}", decode.getClaim(AUTHORITIES_KEY).asString());




//            Collection<? extends GrantedAuthority> authorities =
//                    Arrays.stream(decode.getClaim(AUTHORITIES_KEY).asString().split(","))
//                            .map(SimpleGrantedAuthority::new)
//                            .collect(Collectors.toList());


            return VerifyResult.builder().
                    email(decode.getSubject())
//                    .authorities(authorities)
                    .result(true).build();

        } catch (JWTVerificationException ex) {
            DecodedJWT decode = JWT.decode(token);

            return VerifyResult.builder()
                    .email(decode.getSubject())
                    .result(false).build();
        }
    }

    public Authentication getAuthentication(UserAccount user) {


        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());


    }


}
