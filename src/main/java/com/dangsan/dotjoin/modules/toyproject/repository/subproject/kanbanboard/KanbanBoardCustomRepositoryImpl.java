package com.dangsan.dotjoin.modules.toyproject.repository.subproject.kanbanboard;


import com.dangsan.dotjoin.modules.toyproject.dto.kanban.KanbanCardDto;

import com.dangsan.dotjoin.modules.toyproject.model.subproject.QKanbanBoard;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.QKanbanCard;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.QKanbanList;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.dangsan.dotjoin.modules.toyproject.model.subproject.QKanbanBoard.kanbanBoard;
import static com.dangsan.dotjoin.modules.toyproject.model.subproject.QKanbanList.kanbanList;
import static com.dangsan.dotjoin.modules.toyproject.model.subproject.QKanbanCard.kanbanCard;

@RequiredArgsConstructor
@Slf4j
public class KanbanBoardCustomRepositoryImpl implements KanbanBoardCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<KanbanCardDto> findAllKanbanCardDto(Long kanbanBoardId) {
        QKanbanBoard b = kanbanBoard;
        QKanbanList l = kanbanList;
        QKanbanCard c = kanbanCard;

        List<KanbanCardDto> kanbanCardDtoList =
                jpaQueryFactory.select(Projections.bean(KanbanCardDto.class, b.id, b.BoardName, l.id, l.title, l.detail, c.title, c.detail))
                        .from(b)
                        .innerJoin(b, l.kanbanBoard)
                        .innerJoin(l, c.kanbanList)
                        .fetch();

        kanbanCardDtoList.stream().forEach(kanbanCardDto -> {
            log.info("kanbanboard name : " + kanbanCardDto.getKanbanBoardName());
            log.info("kanbanlist title : " + kanbanCardDto.getKanbanListTitle());
            log.info("kanbanlist detail : " + kanbanCardDto.getKanbanListDetail());
            log.info("kanbancard title : " + kanbanCardDto.getKanbanCardTitle());
            log.info("kanbancard detail : " + kanbanCardDto.getKanbanCardDetail());
        });

        return kanbanCardDtoList;

    }
}
