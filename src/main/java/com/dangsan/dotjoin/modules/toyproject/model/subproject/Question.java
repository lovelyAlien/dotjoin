package com.dangsan.dotjoin.modules.toyproject.model.subproject;

import com.dangsan.dotjoin.modules.account.model.Account;
import com.dangsan.dotjoin.modules.toyproject.dto.question.UpdateTargetQuestionDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    private SubProject subProject;


    @ManyToOne
    private Account questioner;


    @Column
    private String title;

    @Column
    private String detail;

    @Column
    private double rate;

    @OneToMany(mappedBy = "question", orphanRemoval = true)
    private List<Answer> answers = new ArrayList<Answer>();

    @Builder
    public Question(SubProject subProject, Account questioner){
        this.subProject=subProject;
        this.questioner=questioner;

    }


    public void update(UpdateTargetQuestionDto updateTargetQuestionDto){
        this.title=updateTargetQuestionDto.getTitle();
        this.detail=updateTargetQuestionDto.getDetail();
    }





}
