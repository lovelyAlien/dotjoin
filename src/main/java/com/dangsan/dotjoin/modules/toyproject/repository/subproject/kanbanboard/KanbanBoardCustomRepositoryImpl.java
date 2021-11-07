package com.dangsan.dotjoin.modules.toyproject.repository.subproject.kanbanboard;


import com.dangsan.dotjoin.modules.toyproject.dto.kanbanboard.KanbanBoardDto;

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

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class KanbanBoardCustomRepositoryImpl implements KanbanBoardCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<KanbanBoardDto> findAllKanbanBoardDto(Long kanbanBoardId) {
        QKanbanBoard b = kanbanBoard;
        QKanbanList l = kanbanList;
        QKanbanCard c = kanbanCard;

        List<KanbanBoardDto> kanbanBoardDtoList =
                jpaQueryFactory.select(Projections.bean(KanbanBoardDto.class, b.BoardName, l.title, l.detail, c.title, c.detail))
                        .from(b)
                        .innerJoin(b.kanbanList, l)
                        .innerJoin(l.kanbanCard, c)
                        .fetch();

        kanbanBoardDtoList.stream().forEach(kanbanBoardDto -> {
            log.info("member name : " + kanbanBoardDto.getKanbanBoardName());
            log.info("member age : " + kanbanBoardDto.getKanbanListTitle());
            log.info("member age : " + kanbanBoardDto.getKanbanListDetail());
            log.info("member age : " + kanbanBoardDto.getKanbanCardTitle());
            log.info("member age : " + kanbanBoardDto.getKanbanCardDetail());
        });

        return kanbanBoardDtoList;

    }
}
