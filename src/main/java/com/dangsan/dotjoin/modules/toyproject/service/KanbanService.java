package com.dangsan.dotjoin.modules.toyproject.service;

import com.dangsan.dotjoin.modules.toyproject.dto.kanban.*;

import com.dangsan.dotjoin.modules.toyproject.model.subproject.KanbanBoard;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.KanbanCard;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.KanbanList;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.SubProject;
import com.dangsan.dotjoin.modules.toyproject.repository.subproject.SubProjectRepository;
import com.dangsan.dotjoin.modules.toyproject.repository.subproject.kanbanboard.KanbanBoardRepository;
import com.dangsan.dotjoin.modules.toyproject.repository.subproject.kanbanboard.KanbanCardRepository;
import com.dangsan.dotjoin.modules.toyproject.repository.subproject.kanbanboard.KanbanListRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class KanbanService {

    private final KanbanBoardRepository kanbanBoardRepository;
    private final KanbanListRepository kanbanListRepository;
    private final KanbanCardRepository kanbanCardRepository;
    private final SubProjectRepository subProjectRepository;

    //CRUD of KanbanCard
    public KanbanCard inquireTargetKanbanCard(Long kanbanCardId){
        return kanbanCardRepository.findById(kanbanCardId).get();
    }

    public Long registerKanbanCard(RegisterCardDto registerCardDto){

        Long kanbanListId= registerCardDto.getKanbanListId();
        KanbanList kanbanList= kanbanListRepository.findById(kanbanListId).get();
        KanbanCard kanbanCard=new KanbanCard(registerCardDto);
        kanbanList.addCard(kanbanCard);
        kanbanCardRepository.save(kanbanCard);

        return kanbanCard.getId();

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

    public Long registerKanbanList(Long kanbanBoardId, RegisterListDto registerListDto){

        KanbanBoard kanbanBoard=kanbanBoardRepository.getById(kanbanBoardId);

        KanbanList kanbanList=new KanbanList(registerListDto);

        kanbanBoard.addKanbanList(kanbanList);

        kanbanListRepository.save(kanbanList);

        return kanbanList.getId();
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


    //CRUD of KanbanBoard

    public InquireTargetBoardDto inquireTargetKanbanBoard (Long kanbanBoardId) {

        KanbanBoard kanbanBoard=kanbanBoardRepository.findById(kanbanBoardId).get();

        InquireTargetBoardDto inquireTargetBoardDto = new InquireTargetBoardDto(kanbanBoard);

        return inquireTargetBoardDto;

    }

    public Long registerKanbanBoard(Long subProjectId, String boardName){

        SubProject subProject= subProjectRepository.findById(subProjectId).get();


        KanbanBoard kanbanBoard= kanbanBoardRepository.save(new KanbanBoard(subProject, boardName));

        return kanbanBoard.getId();



    }

    @Transactional
    public void updateTargetKanbanBoard(Long kanbanBoardId, String boardName){

        KanbanBoard kanbanBoard=kanbanBoardRepository.findById(kanbanBoardId).get();

        kanbanBoard.setBoardName(boardName);

        kanbanBoardRepository.save(kanbanBoard);
    }


    @Transactional
    public void deleteTargetKanbanBoard(Long kanbanBoardId){

        kanbanBoardRepository.deleteById(kanbanBoardId);

    }


}
