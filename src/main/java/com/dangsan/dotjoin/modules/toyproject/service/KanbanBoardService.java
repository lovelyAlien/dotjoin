package com.dangsan.dotjoin.modules.toyproject.service;

import com.dangsan.dotjoin.modules.toyproject.dto.kanban.CardDto;
import com.dangsan.dotjoin.modules.toyproject.dto.kanban.KanbanCardDto;

import com.dangsan.dotjoin.modules.toyproject.dto.kanban.UpdateTargetCardDto;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.KanbanCard;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.KanbanList;
import com.dangsan.dotjoin.modules.toyproject.repository.subproject.kanbanboard.KanbanBoardRepository;
import com.dangsan.dotjoin.modules.toyproject.repository.subproject.kanbanboard.KanbanCardRepository;
import com.dangsan.dotjoin.modules.toyproject.repository.subproject.kanbanboard.KanbanListRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KanbanBoardService {

    private final KanbanBoardRepository kanbanBoardRepository;
    private final KanbanListRepository kanbanListRepository;
    private final KanbanCardRepository kanbanCardRepository;

    public List<KanbanCardDto> inquireTargetKanbanBoard (Long kanbanBoardId) {
        return kanbanBoardRepository.findAllKanbanCardDto(kanbanBoardId);

    }

    public void registerKanbanCard(Long kanbanBoardId, CardDto cardDto){

        Long kanbanListId= cardDto.getKanbanListId();
        KanbanList kanbanList= kanbanListRepository.findById(kanbanListId).get();
        KanbanCard kanbanCard=new KanbanCard(cardDto);
        kanbanList.addCard(kanbanCard);
        kanbanCardRepository.save(kanbanCard);

    }

    @Transactional
    public void updateTargetKanbanCard(Long kanbanBoardId, UpdateTargetCardDto updateTargetCardDto){

        Long kanbanCardId=updateTargetCardDto.getKanbanCardId();
        Long kanbanListId=updateTargetCardDto.getKanbanListId();
        String title=updateTargetCardDto.getTitle();
        String detail=updateTargetCardDto.getDetail();
        KanbanCard kanbanCard= kanbanCardRepository.findById(kanbanCardId).get();
        KanbanList kanbanList=kanbanListRepository.findById(kanbanListId).get();


        kanbanCard.update(kanbanList, title, detail);
        kanbanCardRepository.save(kanbanCard);

    }




}
