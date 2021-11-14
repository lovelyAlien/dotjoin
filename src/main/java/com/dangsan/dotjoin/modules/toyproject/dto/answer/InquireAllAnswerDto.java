package com.dangsan.dotjoin.modules.toyproject.dto.answer;


import com.dangsan.dotjoin.modules.account.model.Account;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.Answer;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InquireAllAnswerDto {

    private Long answerId;

    private AnswererDto answererDto;

    private String detail;

    public InquireAllAnswerDto(Answer answer){
        this.answerId=answer.getId();

        this.answererDto=new AnswererDto(answer.getAnswerer());

        this.detail=answer.getDetail();
    }

}
