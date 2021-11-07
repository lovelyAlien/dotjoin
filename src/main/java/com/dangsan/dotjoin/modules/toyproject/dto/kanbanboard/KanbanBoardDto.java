package com.dangsan.dotjoin.modules.toyproject.dto.kanbanboard;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KanbanBoardDto {

    private Long id;

    private String kanbanBoardName;

    private String KanbanListTitle;

    private String KanbanListDetail;

    private String KanbanCardTitle;

    private String KanbanCardDetail;

}
