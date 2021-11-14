package com.dangsan.dotjoin.modules.toyproject.dto.kanban;


import com.dangsan.dotjoin.modules.toyproject.model.subproject.KanbanCard;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.KanbanList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InquireAllListDto {

    private Long kanbanListId;

    private String title;

    private String detail;

    private List<KanbanCard> kanbanCards;


    public InquireAllListDto(KanbanList kanbanList){

        this.kanbanListId=kanbanList.getId();

        this.title=kanbanList.getTitle();

        this.detail=kanbanList.getDetail();

        this.kanbanCards=kanbanList.getKanbanCards();
    }

}
