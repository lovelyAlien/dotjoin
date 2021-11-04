package com.dangsan.dotjoin.modules.toyproject.model.subproject;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Memoir {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @ManyToOne
    private SubProject subProject;

    @Column
    private String title;

    @Column
    private String whatDid;

    @Column
    private String whatLearn;

    @Column
    private String whatProblem;

    @Column
    private String howSolution;

    @Column
    private String whyReason;

    @Column
    @OneToMany(mappedBy = "memoir")
    private List<Url> referenceURL= new ArrayList<Url>();

    @Column
    @OneToOne(mappedBy = "memoir")
    private Url gitURL;

    @Column
    private LocalDate developDate;

    @Column
    private LocalDateTime insertDate;

    @Column
    private LocalDateTime updateDate;

    @Column
    private LocalDateTime deleteDate;

    @Column
    private boolean deleteData = false;
}
