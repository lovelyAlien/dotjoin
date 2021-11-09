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


    private Long answerLikeId;

    private Long raterId;

    private String raterName;

    private double rate;


    public AnswerLikeDto(AnswerLike answerLike){
        this.answerLikeId= answerLike.getId();
        this.rate=answerLike.getRate();
        Account rater=answerLike.getRater();
        this.raterId=rater.getId();
        this.raterName=rater.getNickname();

    }

}
