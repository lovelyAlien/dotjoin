package com.dangsan.dotjoin.modules.toyproject.dto.kanban;


import com.dangsan.dotjoin.modules.toyproject.model.subproject.KanbanBoard;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.KanbanCard;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.KanbanList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InquireTargetBoardDto {


    private Long boardId;

    private String boardName;

    private List<KanbanList> kanbanLists;


    public InquireTargetBoardDto(KanbanBoard kanbanBoard){
        this.boardId=kanbanBoard.getId();
        this.boardName=kanbanBoard.getBoardName();
        this.kanbanLists=kanbanBoard.getKanbanLists();
    }


}
