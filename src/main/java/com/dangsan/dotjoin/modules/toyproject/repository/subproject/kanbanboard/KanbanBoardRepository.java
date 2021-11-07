package com.dangsan.dotjoin.modules.toyproject.repository.subproject.kanbanboard;

import com.dangsan.dotjoin.modules.toyproject.model.subproject.KanbanBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KanbanBoardRepository extends JpaRepository<KanbanBoard, Long>, KanbanBoardCustomRepository{
}
