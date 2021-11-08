package com.dangsan.dotjoin.modules.toyproject.repository.subproject.kanbanboard;

import com.dangsan.dotjoin.modules.toyproject.model.subproject.KanbanBoard;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.KanbanList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KanbanListRepository extends JpaRepository<KanbanList, Long> {


}
