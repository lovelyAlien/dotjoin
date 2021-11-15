package com.dangsan.dotjoin.modules.toyproject.dto.answer;

import com.dangsan.dotjoin.modules.toyproject.model.subproject.Answer;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.AnswerLike;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDto {

    private Long answerId;

    private AnswererDto answererDto;

    private List<AnswerLikeDto> answerLikeDtoList=new ArrayList<>();

    private String detail;

    public AnswerDto(Answer answer){
        this.answerId=answer.getId();
        this.detail=answer.getDetail();

        this.answererDto=new AnswererDto(answer.getAnswerer());

        for(AnswerLike answerLike: answer.getAnswerLikes()){
            AnswerLikeDto answerLikeDto=new AnswerLikeDto(answerLike);
            answerLikeDtoList.add(answerLikeDto);
        }

    }


}
