package com.dangsan.dotjoin.modules.toyproject.repository.subproject.kanbanboard;


import com.dangsan.dotjoin.modules.toyproject.model.subproject.KanbanCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KanbanCardRepository extends JpaRepository<KanbanCard, Long>{
}
