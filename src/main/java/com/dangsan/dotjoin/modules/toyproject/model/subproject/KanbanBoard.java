package com.dangsan.dotjoin.modules.toyproject.model.subproject;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private SubProject subProject;

    @OneToMany(mappedBy="kanbanBoard", orphanRemoval = true)
    private List<KanbanList> kanbanLists=new ArrayList<KanbanList>();

    @Column
    private String boardName;


    public void addKanbanList(KanbanList kanbanList){
        kanbanList.setKanbanBoard(this);
        this.kanbanLists.add(kanbanList);
    }

    public KanbanBoard(SubProject subProject, String boarName){
        this.subProject=subProject;
        this.boardName=boarName;

    }




}
