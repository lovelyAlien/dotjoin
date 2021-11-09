package com.dangsan.dotjoin.modules.toyproject.model.subproject;

import com.dangsan.dotjoin.modules.account.model.Account;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    private Question question;


    @OneToOne
    private Account answerer;

    @Column
    private String detail;

    @OneToMany(mappedBy = "answer")
    private List<AnswerLike> answerLikes = new ArrayList<AnswerLike>();
}
