package com.dangsan.dotjoin.modules.toyproject.controller;


import com.dangsan.dotjoin.modules.toyproject.dto.kanban.RegisterCardDto;
import com.dangsan.dotjoin.modules.toyproject.dto.kanban.KanbanCardDto;
import com.dangsan.dotjoin.modules.toyproject.dto.kanban.UpdateTargetCardDto;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.KanbanCard;
import com.dangsan.dotjoin.modules.toyproject.service.KanbanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/toyprojects/{projectId}/subprojects/{subprojectId}")
public class KanbanController {
    private final KanbanService kanbanService;


    //CRUD of KanbanCard
    @GetMapping("/kanbanboards/{kanbanBoardId}")
    public ResponseEntity<?> inquireTargetKanbanCard (@RequestBody Long kanbanCardId) {

        KanbanCard kanbanCard= kanbanService.inquireTargetKanbanCard(kanbanCardId);

        return ResponseEntity.ok(kanbanCard);
    }


    @PostMapping("/kanbanboards/{kanbanBoardId}")
    public ResponseEntity<?> registerKanbanCard (@PathVariable Long kanbanBoardId, @RequestBody RegisterCardDto registerCardDto) {

        kanbanService.registerKanbanCard(kanbanBoardId, registerCardDto);

        return ResponseEntity.ok(HttpStatus.OK);
    }


    @PutMapping("/kanbanboards/{kanbanBoardId}")
    public ResponseEntity<?> updateTargetKanbanCard (@PathVariable Long kanbanBoardId, @RequestBody UpdateTargetCardDto updateTargetCardDto) {

        kanbanService.updateTargetKanbanCard(kanbanBoardId, updateTargetCardDto);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/kanbanboards/{kanbanBoardId}")
    public ResponseEntity<?> deleteTargetKanbanCard (@PathVariable Long kanbanBoardId, @RequestBody Long kanbanCardId) {

        kanbanService.deleteTargetKanbanCard(kanbanCardId);

        return ResponseEntity.ok(HttpStatus.OK);
    }


    //CRUD of KanbanList
    @PostMapping
    @GetMapping("/kanbanboards/{kanbanBoardId}")
    public ResponseEntity<?> inquireTargetKanbanBoard (@PathVariable Long kanbanBoardId) {


        List<KanbanCardDto> kanbanCardDtoList= kanbanService.inquireTargetKanbanBoard(kanbanBoardId);
        return ResponseEntity.ok(kanbanCardDtoList);
    }



}
