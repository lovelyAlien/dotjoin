package com.dangsan.dotjoin.modules.toyproject.dto.memoir;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUrlDto {

    private String type;////GIT or REF

    private String url;


}
