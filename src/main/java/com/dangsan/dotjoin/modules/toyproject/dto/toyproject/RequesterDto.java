package com.dangsan.dotjoin.modules.toyproject.dto.toyproject;

import com.dangsan.dotjoin.modules.account.model.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequesterDto {
    private Long requesterId;

    private String nickname;

    public RequesterDto(Account account){
        this.requesterId=account.getId();
        this.nickname=account.getNickname();
    }

}
