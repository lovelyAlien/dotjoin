package com.dangsan.dotjoin.modules.toyproject.dto.question;


import com.dangsan.dotjoin.modules.account.model.Account;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionerDto {


    private Long questionerId;


    private String questionerNickName;

    public QuestionerDto(Account questioner){
        this.questionerId=questioner.getId();
        this.questionerNickName=questioner.getNickname();
    }
}
