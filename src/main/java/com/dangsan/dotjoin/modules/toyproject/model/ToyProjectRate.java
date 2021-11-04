package com.dangsan.dotjoin.modules.toyproject.model;

import com.dangsan.dotjoin.modules.account.model.Account;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class ToyProjectRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    private ToyProject toyProject;


    @OneToOne
    private Account rater;

    @Column
    private double rateScore;
}
