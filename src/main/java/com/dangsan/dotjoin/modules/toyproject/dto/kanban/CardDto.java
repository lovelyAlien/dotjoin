package com.dangsan.dotjoin.modules.toyproject.dto.kanban;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {



    private String title;

    private String detail;

    private Long kanbanListId;


}
