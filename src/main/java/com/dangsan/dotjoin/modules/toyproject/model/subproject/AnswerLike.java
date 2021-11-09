package com.dangsan.dotjoin.modules.toyproject.model.subproject;

import com.dangsan.dotjoin.modules.account.model.Account;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.mapping.ToOne;

import javax.persistence.*;

@Entity
@Getter @Setter
public class AnswerLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    private Answer answer;


    @OneToOne
    private Account rater;

    @Column
    private double rate=0;
}
