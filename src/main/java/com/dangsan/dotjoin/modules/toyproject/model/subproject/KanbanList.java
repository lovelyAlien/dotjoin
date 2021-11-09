package com.dangsan.dotjoin.modules.toyproject.model.subproject;


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
public class KanbanList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    private KanbanBoard kanbanBoard;


    @OneToMany(mappedBy = "kanbanList")
    private List<KanbanCard> kanbanCards=new ArrayList<KanbanCard>();


    @Column
    private String title;

    @Column
    private String detail;



    public KanbanList(String title){
        this.title=title;
    }

    public void addCard(KanbanCard kanbanCard){
        kanbanCard.setKanbanList(this);
        this.kanbanCards.add(kanbanCard);

    }
}
