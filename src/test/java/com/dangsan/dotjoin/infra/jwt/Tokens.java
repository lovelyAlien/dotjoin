package com.dangsan.dotjoin.infra.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tokens {

    private String accessToken;
    private String refreshToken;

}

