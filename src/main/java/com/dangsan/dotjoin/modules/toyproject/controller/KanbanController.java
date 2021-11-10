package com.dangsan.dotjoin.modules.toyproject.controller;


import com.dangsan.dotjoin.modules.toyproject.dto.kanban.*;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.KanbanCard;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.KanbanList;
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
    @GetMapping("/kanbancards/{kanbanCardId}")
    public ResponseEntity<?> inquireTargetKanbanCard (@PathVariable Long kanbanCardId) {

        KanbanCard kanbanCard= kanbanService.inquireTargetKanbanCard(kanbanCardId);

        return ResponseEntity.ok(kanbanCard);
    }


    @PostMapping("/kanbancards")
    public ResponseEntity<?> registerKanbanCard (@RequestBody RegisterCardDto registerCardDto) {

        kanbanService.registerKanbanCard(registerCardDto);

        return ResponseEntity.ok(HttpStatus.OK);
    }


    @PutMapping("/kanbancards/{kanbanCardId}")
    public ResponseEntity<?> updateTargetKanbanCard (@PathVariable Long kanbanCardId, @RequestBody UpdateTargetCardDto updateTargetCardDto) {

        kanbanService.updateTargetKanbanCard(kanbanCardId, updateTargetCardDto);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/kanbancards/{kanbanCardId}")
    public ResponseEntity<?> deleteTargetKanbanCard (@PathVariable Long kanbanCardId) {

        kanbanService.deleteTargetKanbanCard(kanbanCardId);

        return ResponseEntity.ok(HttpStatus.OK);
    }


    //CRUD of KanbanList
    @GetMapping("/kanbanlists/{kanbanListId}")
    public ResponseEntity<?> inquireTargetKanbanList (@PathVariable Long kanbanListId) {

        KanbanList kanbanList= kanbanService.inquireTargetKanbanList(kanbanListId);

        return ResponseEntity.ok(kanbanList);
    }

    @PostMapping("/kanbanboards/{kanbanBoardId}/kanbanlists")
    public ResponseEntity<?> registerKanbanList (@PathVariable Long kanbanBoardId, @RequestBody RegisterListDto registerListDto) {

        kanbanService.registerKanbanList(kanbanBoardId, registerListDto);

        return ResponseEntity.ok(HttpStatus.OK);

    }

    @PutMapping("/kanbanlists/{kanbanListId}")
    public ResponseEntity<?> updateTargetKanbanList (@PathVariable Long kanbanListId, @RequestBody UpdateTargetListDto updateTargetListDto) {

        kanbanService.updateTargetKanbanList(kanbanListId, updateTargetListDto);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/kanbanlists/{kanbanListId}")
    public ResponseEntity<?> deleteTargetKanbanList (@PathVariable Long kanbanListId) {

        kanbanService.deleteTargetKanbanList(kanbanListId);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    //CRUD of KanbanBoard




}
