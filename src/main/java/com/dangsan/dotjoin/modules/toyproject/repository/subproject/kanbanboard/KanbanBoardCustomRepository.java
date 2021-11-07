package com.dangsan.dotjoin.modules.toyproject.repository.subproject.kanbanboard;

import com.dangsan.dotjoin.modules.toyproject.dto.kanbanboard.KanbanBoardDto;

import java.util.List;

public interface KanbanBoardCustomRepository {

    List<KanbanBoardDto> findAllKanbanBoardDto(Long kanbanBoardId);
}
