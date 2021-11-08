package com.dangsan.dotjoin.modules.toyproject.model.subproject;


import com.dangsan.dotjoin.modules.toyproject.dto.kanbanboard.KanbanCardDto;
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
    private KanbanList kanbanList;


    @Column
    private String title;


    @Column
    private String detail;


    public KanbanCard(KanbanCardDto kanbanCardDto){
        this.title=kanbanCardDto.getTitle();
        this.detail=kanbanCardDto.getDetail();
    }

}
