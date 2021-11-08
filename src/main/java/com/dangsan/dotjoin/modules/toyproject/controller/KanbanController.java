package com.dangsan.dotjoin.modules.toyproject.controller;


import com.dangsan.dotjoin.modules.toyproject.dto.kanbanboard.KanbanBoardDto;
import com.dangsan.dotjoin.modules.toyproject.dto.kanbanboard.KanbanCardDto;
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


        List<KanbanBoardDto> kanbanboardDtoList= kanbanBoardService.inquireAllKanbanBoardDto(kanbanBoardId);
        return ResponseEntity.ok(kanbanboardDtoList);
    }


    @PostMapping("/kanbanboards/{kanbanBoardId}")
    public ResponseEntity<?> registerKanbanCards (@PathVariable Long kanbanBoardId, @RequestBody List<KanbanCardDto> kanbanCardDtoList) {

        kanbanBoardService.registerKanbanCards(kanbanCardDtoList);

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
