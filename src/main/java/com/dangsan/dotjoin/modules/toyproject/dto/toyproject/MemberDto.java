package com.dangsan.dotjoin.modules.toyproject.dto.toyproject;

import com.dangsan.dotjoin.modules.account.model.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private Long memberId;

    private String nickname;

    public MemberDto(Account account){
        this.memberId=account.getId();

        this.nickname=account.getNickname();
    }
}
