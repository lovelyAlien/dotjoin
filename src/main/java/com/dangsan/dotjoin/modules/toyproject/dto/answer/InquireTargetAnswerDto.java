package com.dangsan.dotjoin.modules.toyproject.dto.answer;

import com.dangsan.dotjoin.modules.toyproject.model.subproject.Answer;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.AnswerLike;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class InquireTargetAnswerDto {

    private Long questionId;

    private Long answerId;

    private AnswererDto answererDto;

    private String detail;

    private List<AnswerLikeDto> answerLikeDtoList=new ArrayList<>();

    public InquireTargetAnswerDto(Long questionId, Answer answer){
        this.questionId=questionId;
        this.answerId=answer.getId();
        this.answererDto=new AnswererDto(answer.getAnswerer());
        this.detail=answer.getDetail();

        for(AnswerLike answerLike: answer.getAnswerLikes()){
            this.answerLikeDtoList.add(new AnswerLikeDto(answerLike));
        }
    }

}
