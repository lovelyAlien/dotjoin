package com.dangsan.dotjoin.modules.toyproject.dto.question;


import com.dangsan.dotjoin.modules.account.model.Account;
import com.dangsan.dotjoin.modules.toyproject.dto.answer.AnswerDto;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto {

    private Long questionId;

    private String content;

    private Long questionerId;

    private String questionerName;

    private List<AnswerDto> answerDtoList;

    public QuestionDto(Question question){
        this.questionId=question.getId();
        this.content=question.getContent();
        Account questioner= question.getQuestioner();
        this.questionerId= questioner.getId();
        this.questionerName=questioner.getNickname();
    }

}
