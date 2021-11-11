package com.dangsan.dotjoin.modules.toyproject.dto.answer;

import com.dangsan.dotjoin.modules.account.model.Account;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.AnswerLike;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AnswerLikeDto {

    private Long raterId;

    private String raterNickName;

    private double rate;

    public AnswerLikeDto(AnswerLike answerLike){
        Account rater=answerLike.getRater();
        this.raterId=rater.getId();
        this.raterNickName=rater.getNickname();
        this.rate=answerLike.getRate();
    }

}
