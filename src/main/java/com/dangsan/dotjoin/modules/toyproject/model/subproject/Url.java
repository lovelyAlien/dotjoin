package com.dangsan.dotjoin.modules.toyproject.model.subproject;


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
    private String type; //GIT or REF

    @Column
    private String url;

    @ManyToOne
    private Memoir memoir;


}
