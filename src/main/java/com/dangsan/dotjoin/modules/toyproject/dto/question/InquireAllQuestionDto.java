package com.dangsan.dotjoin.modules.toyproject.dto.question;

import com.dangsan.dotjoin.modules.account.model.Account;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.Question;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.SubProject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InquireAllQuestionDto {

    private Long questionId;

    private QuestionerDto questionerDto;

    private String title;

    private String detail;

    private double rate;

    public InquireAllQuestionDto(Question question){
         this.questionId=question.getId();

         this.questionerDto=new QuestionerDto(question.getQuestioner());

         this.title=question.getTitle();

         this.detail=question.getDetail();

         this.rate=question.getRate();
    }

}
