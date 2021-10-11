package com.dangsan.dotjoin.modules.follow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FollowDto {

    private Long id;
    private String nickname;
    private String profileImgUrl;
    private Boolean followState;
    private Boolean loginUser;

}
