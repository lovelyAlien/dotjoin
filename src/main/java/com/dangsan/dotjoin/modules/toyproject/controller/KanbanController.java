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

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/toyprojects/{projectId}/subprojects/{subProjectId}")
public class KanbanController {
    private final KanbanService kanbanService;


    //CRUD of KanbanCard
    @GetMapping("/kanbanboards/{kanbanBoardId}/kanbanlists/{kanbanListId}/kanbancards/{kanbanCardId}")
    public ResponseEntity<?> inquireTargetKanbanCard (@PathVariable Long kanbanCardId) {

        KanbanCard kanbanCard= kanbanService.inquireTargetKanbanCard(kanbanCardId);

        return ResponseEntity.ok(kanbanCard);
    }


    @PostMapping("/kanbanboards/{kanbanBoardId}/kanbanlists/{kanbanListId}/kanbancards")
    public ResponseEntity<?> registerKanbanCard (@RequestBody RegisterCardDto registerCardDto) {

        Long kanbanCardId= kanbanService.registerKanbanCard(registerCardDto);

        return ResponseEntity.ok(kanbanCardId);
    }


    @PutMapping("/kanbanboards/{kanbanBoardId}/kanbanlists/{kanbanListId}/kanbancards/{kanbanCardId}")
    public ResponseEntity<?> updateTargetKanbanCard (@PathVariable Long kanbanCardId, @RequestBody UpdateTargetCardDto updateTargetCardDto) {

        kanbanService.updateTargetKanbanCard(kanbanCardId, updateTargetCardDto);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/kanbanboards/{kanbanBoardId}/kanbanlists/{kanbanListId}/kanbancards/{kanbanCardId}")
    public ResponseEntity<?> deleteTargetKanbanCard (@PathVariable Long kanbanCardId) {

        kanbanService.deleteTargetKanbanCard(kanbanCardId);

        return ResponseEntity.ok(HttpStatus.OK);
    }


    //CRUD of KanbanList
    @GetMapping("/kanbanboards/{kanbanBoardId}/kanbanlists/{kanbanListId}")
    public ResponseEntity<?> inquireTargetKanbanList (@PathVariable Long kanbanListId) {

        KanbanList kanbanList= kanbanService.inquireTargetKanbanList(kanbanListId);

        return ResponseEntity.ok(kanbanList);
    }

    @PostMapping("/kanbanboards/{kanbanBoardId}/kanbanlists")
    public ResponseEntity<?> registerKanbanList (@PathVariable Long kanbanBoardId, @RequestBody RegisterListDto registerListDto) {

        Long kanbanListId= kanbanService.registerKanbanList(kanbanBoardId, registerListDto);

        return ResponseEntity.ok(kanbanListId);

    }

    @PutMapping("/kanbanboards/{kanbanBoardId}/kanbanlists/{kanbanListId}")
    public ResponseEntity<?> updateTargetKanbanList (@PathVariable Long kanbanListId, @RequestBody UpdateTargetListDto updateTargetListDto) {

        kanbanService.updateTargetKanbanList(kanbanListId, updateTargetListDto);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/kanbanboards/{kanbanBoardId}/kanbanlists/{kanbanListId}")
    public ResponseEntity<?> deleteTargetKanbanList (@PathVariable Long kanbanListId) {

        kanbanService.deleteTargetKanbanList(kanbanListId);

        return ResponseEntity.ok(HttpStatus.OK);
    }



    //CRUD of KanbanBoard

    @GetMapping("/kanbanboards/{kanbanBoardId}")
    public ResponseEntity<?> inquireTargetKanbanBoard (@PathVariable Long kanbanBoardId) {

        InquireTargetBoardDto inquireTargetBoardDto = kanbanService.inquireTargetKanbanBoard(kanbanBoardId);
        return ResponseEntity.ok(inquireTargetBoardDto);
    }


    @PostMapping("/kanbanboards")
    public ResponseEntity<?> registerKanbanBoard (@PathVariable Long subProjectId, @RequestBody String boardName) {

        Long kanbanBoardId= kanbanService.registerKanbanBoard(subProjectId, boardName);

        return ResponseEntity.ok(kanbanBoardId);
    }

    @PutMapping("/kanbanboards/{kanbanBoardId}")
    public ResponseEntity<?> updateTargetKanbanList (@PathVariable Long kanbanBoardId, @RequestBody String boardName) {

        kanbanService.updateTargetKanbanBoard(kanbanBoardId, boardName);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/kanbanboards/{kanbanBoardId}")
    public ResponseEntity<?> deleteTargetKanbanBoard (@PathVariable Long kanbanBoardId) {

        kanbanService.deleteTargetKanbanBoard(kanbanBoardId);

        return ResponseEntity.ok(HttpStatus.OK);
    }


}
