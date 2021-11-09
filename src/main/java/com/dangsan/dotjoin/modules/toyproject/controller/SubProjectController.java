package com.dangsan.dotjoin.modules.toyproject.controller;



import com.dangsan.dotjoin.modules.toyproject.dto.kanbanboard.KanbanBoardDto;
import com.dangsan.dotjoin.modules.toyproject.dto.kanbanboard.KanbanCardDto;
import com.dangsan.dotjoin.modules.toyproject.dto.question.QuestionDto;
import com.dangsan.dotjoin.modules.toyproject.dto.question.RegisterQuestion;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.KanbanCard;
import com.dangsan.dotjoin.modules.toyproject.service.KanbanBoardService;
import com.dangsan.dotjoin.modules.toyproject.service.QuestionService;
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
    private final QuestionService questionService;

    @GetMapping("/{subProjectId}")
    public ResponseEntity<?> inquireQuestionDto (@PathVariable Long subProjectId) {


        List<QuestionDto> questionDtoList= questionService.inquireQuestionDto(subProjectId);
        return ResponseEntity.ok(questionDtoList);
    }





}
