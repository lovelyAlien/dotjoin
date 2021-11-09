package com.dangsan.dotjoin.modules.toyproject.dto.answer;


import com.dangsan.dotjoin.modules.account.model.Account;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.Answer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDto {

    private Long answerId;

    private Long answererId;

    private String answererName;

    private String content;

    private List<AnswerLikeDto> answerLikeDtoList;

    private double totalScore;

    public AnswerDto(Answer answer){
        this.answerId=answer.getId();
        this.content=answer.getContent();
        Account answerer=answer.getAnswerer();
        this.answererId=answerer.getId();
        this.answererName=answerer.getNickname();

    }

    public void setAnswerLikeDtoList(List<AnswerLikeDto> answerLikeDtoList, double totalScore){
        this.answerLikeDtoList=answerLikeDtoList;
        this.totalScore=totalScore;
    }


}
