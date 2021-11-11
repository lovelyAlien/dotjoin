package com.dangsan.dotjoin.modules.toyproject.model.subproject;

import com.dangsan.dotjoin.modules.account.model.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JsonIgnore
    private Question question;


    @OneToOne
    private Account answerer;

    @Column
    private String detail;

    @OneToMany(mappedBy = "answer", orphanRemoval = true)
    private List<AnswerLike> answerLikes = new ArrayList<AnswerLike>();

    public Answer(Question question, Account answerer){
        this.question=question;
        this.answerer=answerer;
    }



}
