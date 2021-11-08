package com.dangsan.dotjoin.modules.toyproject.service;

import com.dangsan.dotjoin.modules.toyproject.dto.kanbanboard.KanbanBoardDto;
import com.dangsan.dotjoin.modules.toyproject.dto.kanbanboard.KanbanCardDto;

import com.dangsan.dotjoin.modules.toyproject.model.subproject.KanbanCard;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.KanbanList;
import com.dangsan.dotjoin.modules.toyproject.repository.subproject.kanbanboard.KanbanBoardRepository;
import com.dangsan.dotjoin.modules.toyproject.repository.subproject.kanbanboard.KanbanCardRepository;
import com.dangsan.dotjoin.modules.toyproject.repository.subproject.kanbanboard.KanbanListRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KanbanBoardService {

    private final KanbanBoardRepository kanbanBoardRepository;
    private final KanbanListRepository kanbanListRepository;
    private final KanbanCardRepository kanbanCardRepository;

    public List<KanbanBoardDto> inquireAllKanbanBoardDto (Long kanbanBoardId) {
        return kanbanBoardRepository.findAllKanbanBoardDto(kanbanBoardId);

    }

    public void registerKanbanCards(List<KanbanCardDto> kanbanCardDtoList){


        for(KanbanCardDto kanbanCardDto: kanbanCardDtoList){

            Long kanbanListId= kanbanCardDto.getKanbanListId();
            KanbanList kanbanList= kanbanListRepository.findById(kanbanListId).get();

            KanbanCard kanbanCard= new KanbanCard(kanbanCardDto);
            kanbanList.addCard(kanbanCard);

            kanbanCardRepository.save(kanbanCard);
        }

    }


}
