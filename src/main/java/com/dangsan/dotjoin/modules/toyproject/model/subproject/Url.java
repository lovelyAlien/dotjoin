package com.dangsan.dotjoin.modules.toyproject.model.subproject;

import com.dangsan.dotjoin.modules.account.model.Account;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column
    private String url;

}

