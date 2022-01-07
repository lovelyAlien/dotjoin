package com.dangsan.dotjoin.infra.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VerifyResult {

    private String email;
    private boolean result;


}
