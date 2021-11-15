package com.dangsan.dotjoin.modules.toyproject.dto.kanban;


import com.dangsan.dotjoin.modules.toyproject.model.subproject.KanbanCard;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.KanbanList;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InquireAllCardDto {

    private Long cardId;

    private String title;

    private String detail;

    public InquireAllCardDto(KanbanCard kanbanCard){
        this.cardId=kanbanCard.getId();

        this.title=kanbanCard.getTitle();

        this.detail=kanbanCard.getDetail();
    }
}
