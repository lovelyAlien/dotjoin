package com.dangsan.dotjoin.modules.toyproject.model.subproject;


import com.dangsan.dotjoin.modules.toyproject.model.ToyProject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class KanbanBoard {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private ToyProject toyProject;

    @OneToOne
    private SubProject subProject;

    @OneToMany(mappedBy="kanbanBoard")
    private List<KanbanList> kanbanList=new ArrayList<KanbanList>();

    @Column
    private String BoardName;


    public KanbanBoard(ToyProject toyProject, SubProject subProject){
        this.toyProject= toyProject;
        this.subProject=subProject;
    }


}
