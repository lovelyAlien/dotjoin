package com.dangsan.dotjoin.modules.toyproject.repository.subproject.kanbanboard;

import com.dangsan.dotjoin.modules.toyproject.dto.kanban.KanbanCardDto;

import java.util.List;

public interface KanbanBoardCustomRepository {

    List<KanbanCardDto> findAllKanbanCardDto(Long kanbanBoardId);
}
