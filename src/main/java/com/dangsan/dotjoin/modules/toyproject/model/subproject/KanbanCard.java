package com.dangsan.dotjoin.modules.toyproject.model.subproject;


import com.dangsan.dotjoin.modules.toyproject.dto.kanban.RegisterCardDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class KanbanCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JsonIgnore
    private KanbanList kanbanList;


    @Column
    private String title;


    @Column
    private String detail;


    public KanbanCard(RegisterCardDto registerCardDto){
        this.title= registerCardDto.getTitle();
        this.detail= registerCardDto.getDetail();

    }

    public void update(KanbanList kanbanList, String title, String detail){
        this.kanbanList=kanbanList;
        this.title=title;
        this.detail=detail;

    }



}
