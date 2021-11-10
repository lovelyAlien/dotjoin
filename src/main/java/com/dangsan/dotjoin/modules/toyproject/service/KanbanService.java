package com.dangsan.dotjoin.modules.toyproject.service;

import com.dangsan.dotjoin.modules.toyproject.dto.kanban.*;

import com.dangsan.dotjoin.modules.toyproject.model.subproject.KanbanBoard;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.KanbanCard;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.KanbanList;
import com.dangsan.dotjoin.modules.toyproject.repository.subproject.kanbanboard.KanbanBoardRepository;
import com.dangsan.dotjoin.modules.toyproject.repository.subproject.kanbanboard.KanbanCardRepository;
import com.dangsan.dotjoin.modules.toyproject.repository.subproject.kanbanboard.KanbanListRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KanbanService {

    private final KanbanBoardRepository kanbanBoardRepository;
    private final KanbanListRepository kanbanListRepository;
    private final KanbanCardRepository kanbanCardRepository;

    //CRUD of KanbanCard
    public KanbanCard inquireTargetKanbanCard(Long kanbanCardId){
        return kanbanCardRepository.findById(kanbanCardId).get();
    }

    public void registerKanbanCard(RegisterCardDto registerCardDto){

        Long kanbanListId= registerCardDto.getKanbanListId();
        KanbanList kanbanList= kanbanListRepository.findById(kanbanListId).get();
        KanbanCard kanbanCard=new KanbanCard(registerCardDto);
        kanbanList.addCard(kanbanCard);
        kanbanCardRepository.save(kanbanCard);

    }

    @Transactional
    public void updateTargetKanbanCard(Long kanbanCardId, UpdateTargetCardDto updateTargetCardDto){


        Long kanbanListId=updateTargetCardDto.getKanbanListId();
        String title=updateTargetCardDto.getTitle();
        String detail=updateTargetCardDto.getDetail();
        KanbanCard kanbanCard= kanbanCardRepository.findById(kanbanCardId).get();
        KanbanList kanbanList=kanbanListRepository.findById(kanbanListId).get();


        kanbanCard.update(kanbanList, title, detail);
        kanbanCardRepository.save(kanbanCard);

    }

    @Transactional
    public void deleteTargetKanbanCard(Long kanbanCardId){

        kanbanCardRepository.deleteById(kanbanCardId);
    }

    //CRUD of KanbanList
    public KanbanList inquireTargetKanbanList(Long kanbanListId){
        return kanbanListRepository.findById(kanbanListId).get();
    }

    public void registerKanbanList(Long kanbanBoardId, RegisterListDto registerListDto){

        KanbanBoard kanbanBoard=kanbanBoardRepository.getById(kanbanBoardId);

        KanbanList kanbanList=new KanbanList(registerListDto);

        kanbanBoard.addKanbanList(kanbanList);

        kanbanListRepository.save(kanbanList);


    }

    @Transactional
    public void updateTargetKanbanList(Long kanbanListId, UpdateTargetListDto updateTargetListDto){


        KanbanList kanbanList= kanbanListRepository.findById(kanbanListId).get();

        kanbanList.update(updateTargetListDto);

        kanbanListRepository.save(kanbanList);
    }

    @Transactional
    public void deleteTargetKanbanList(Long kanbanListId){

        kanbanListRepository.deleteById(kanbanListId);

    }







    public List<KanbanCardDto> inquireTargetKanbanBoard (Long kanbanBoardId) {
        return kanbanBoardRepository.findAllKanbanCardDto(kanbanBoardId);

    }


}
