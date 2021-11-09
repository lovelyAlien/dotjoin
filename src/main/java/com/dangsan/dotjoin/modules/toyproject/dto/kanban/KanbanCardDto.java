package com.dangsan.dotjoin.modules.toyproject.dto.kanban;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KanbanCardDto {

    private Long kanbanBoardId;

    private String kanbanBoardName;

    private Long kanbanListId;

    private String kanbanListTitle;

    private String kanbanListDetail;

    private String kanbanCardTitle;

    private String kanbanCardDetail;

}
