package com.dangsan.dotjoin.modules.toyproject.controller;



import com.dangsan.dotjoin.modules.toyproject.dto.question.QuestionDto;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.Memoir;
import com.dangsan.dotjoin.modules.toyproject.service.QuestionService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<?> inquireAllQuestionDto (@PathVariable Long subProjectId) {


        List<QuestionDto> questionDtoList= questionService.inquireAllQuestionDto(subProjectId);
        return ResponseEntity.ok(questionDtoList);
    }


    @GetMapping("/{subProjectId}")
    public ResponseEntity<?> inquireAllMemoirDto (@PathVariable Long subProjectId) {


        return ResponseEntity.ok(null);
    }



}
