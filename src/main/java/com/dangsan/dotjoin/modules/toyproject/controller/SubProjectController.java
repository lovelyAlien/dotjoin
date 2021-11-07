package com.dangsan.dotjoin.modules.toyproject.controller;



import com.dangsan.dotjoin.modules.toyproject.dto.kanbanboard.KanbanBoardDto;
import com.dangsan.dotjoin.modules.toyproject.dto.question.RegisterQuestion;
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
public class SubProjectController {
    private final KanbanBoardService kanbanBoardService;





    @GetMapping("/kanbanboards/{kanbanBoardId}")
    public ResponseEntity<?> inquireAllKanbanBoard (@PathVariable Long kanbanBoardId) {


        List<KanbanBoardDto> kanbanboardDtos= kanbanBoardService.inquireAllKanbanBoardDto(kanbanBoardId);
        return ResponseEntity.ok(kanbanboardDtos);
    }






}
