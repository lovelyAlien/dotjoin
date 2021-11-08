package com.dangsan.dotjoin.modules.toyproject.model.subproject;


import com.dangsan.dotjoin.modules.toyproject.model.ToyProject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class KanbanBoard {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private ToyProject toyProject;

    @OneToOne
    private SubProject subProject;

    @OneToMany(mappedBy="kanbanBoard")
    private List<KanbanList> kanbanLists=new ArrayList<KanbanList>();

    @Column
    private String BoardName;


    public void setToyProjectAndSubProject(ToyProject toyProject, SubProject subProject){
        this.toyProject= toyProject;
        this.subProject=subProject;
    }

    public void addKanbanList(KanbanList kanbanList){
        kanbanList.setKanbanBoard(this);
        this.kanbanLists.add(kanbanList);
    }



}
