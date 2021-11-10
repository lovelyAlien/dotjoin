package com.dangsan.dotjoin.modules.toyproject.model.subproject;


import com.dangsan.dotjoin.modules.toyproject.dto.kanban.RegisterListDto;
import com.dangsan.dotjoin.modules.toyproject.dto.kanban.UpdateTargetListDto;
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
public class KanbanList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JsonIgnore
    private KanbanBoard kanbanBoard;


    @OneToMany(mappedBy = "kanbanList", orphanRemoval = true)
    private List<KanbanCard> kanbanCards=new ArrayList<KanbanCard>();


    @Column
    private String title;

    @Column
    private String detail;

    public KanbanList(RegisterListDto registerListDto){
        this.title=registerListDto.getTitle();
        this.detail=registerListDto.getDetail();

    }

    public void addCard(KanbanCard kanbanCard){
        kanbanCard.setKanbanList(this);
        this.kanbanCards.add(kanbanCard);

    }

    public void update(UpdateTargetListDto updateTargetListDto){
        this.title=updateTargetListDto.getTitle();
        this.detail= updateTargetListDto.getDetail();

    }




}
