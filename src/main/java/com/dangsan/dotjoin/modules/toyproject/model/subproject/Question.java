package com.dangsan.dotjoin.modules.toyproject.model.subproject;

import com.dangsan.dotjoin.modules.account.model.Account;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
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
    private String content;

    @Column
    private double rate;

    @OneToMany(mappedBy = "question")
    private List<Answer> answers = new ArrayList<Answer>();
}
