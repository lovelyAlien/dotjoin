package com.dangsan.dotjoin.modules.toyproject.controller;


import com.dangsan.dotjoin.modules.toyproject.dto.kanban.CardDto;
import com.dangsan.dotjoin.modules.toyproject.dto.kanban.KanbanCardDto;
import com.dangsan.dotjoin.modules.toyproject.service.KanbanBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/subprojects")
public class KanbanController {
    private final KanbanBoardService kanbanBoardService;



    @GetMapping("/kanbanboards/{kanbanBoardId}")
    public ResponseEntity<?> inquireKanbanBoard (@PathVariable Long kanbanBoardId) {


        List<KanbanCardDto> kanbanCardDtoList= kanbanBoardService.inquireAllKanbanCardDto(kanbanBoardId);
        return ResponseEntity.ok(kanbanCardDtoList);
    }


    @PostMapping("/kanbanboards/{kanbanBoardId}")
    public ResponseEntity<?> registerKanbanCard (@PathVariable Long kanbanBoardId, @RequestBody CardDto cardDto) {

        kanbanBoardService.registerKanbanCard(kanbanBoardId, cardDto);

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
