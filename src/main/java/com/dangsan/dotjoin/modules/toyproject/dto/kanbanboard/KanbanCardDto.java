package com.dangsan.dotjoin.modules.toyproject.dto.kanbanboard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KanbanCardDto {

    private String title;

    private String detail;

    private Long kanbanListId;


}
