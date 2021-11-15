package com.dangsan.dotjoin.modules.toyproject.dto.answer;


import com.dangsan.dotjoin.modules.account.model.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswererDto {

    private Long answererId;


    private String answererNickName;

    public AnswererDto(Account answerer){
        this.answererId=answerer.getId();

        this.answererNickName=answerer.getNickname();

    }

}
