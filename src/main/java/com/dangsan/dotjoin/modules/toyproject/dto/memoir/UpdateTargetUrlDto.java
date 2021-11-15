package com.dangsan.dotjoin.modules.toyproject.dto.memoir;


import com.dangsan.dotjoin.modules.toyproject.model.subproject.Url;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTargetUrlDto {

    private Long urlId;

    private String type;

    private String url;////GIT or REF

}
