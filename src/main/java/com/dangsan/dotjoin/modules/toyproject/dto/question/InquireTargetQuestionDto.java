package com.dangsan.dotjoin.modules.toyproject.dto.question;

import com.dangsan.dotjoin.modules.toyproject.dto.answer.AnswerDto;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.Answer;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InquireTargetQuestionDto {

    private Long subProjectId;

    private Long questionId;

    private QuestionerDto questionerDto;

    private String title;

    private String detail;

    private double rate;

    private List<AnswerDto> answerDtoList=new ArrayList<>();

    public InquireTargetQuestionDto(Long subProjectId, Question question){
        this.subProjectId=subProjectId;
        this.questionId=question.getId();
        this.title=question.getTitle();
        this.detail=question.getDetail();
        this.rate=question.getRate();

        this.questionerDto=new QuestionerDto(question.getQuestioner());

        for(Answer answer: question.getAnswers()){
            AnswerDto answerDto=new AnswerDto(answer);
            answerDtoList.add(answerDto);
        }
    }


}
