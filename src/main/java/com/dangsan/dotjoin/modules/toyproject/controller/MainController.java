package com.dangsan.dotjoin.modules.toyproject.controller;

import com.dangsan.dotjoin.modules.toyproject.dto.memoir.InquireAllMemoirDto;

import com.dangsan.dotjoin.modules.toyproject.dto.question.InquireAllQuestionDto;
import com.dangsan.dotjoin.modules.toyproject.dto.toyproject.InquireAllToyProjectDto;

import com.dangsan.dotjoin.modules.toyproject.service.MemoirService;
import com.dangsan.dotjoin.modules.toyproject.service.QuestionService;
import com.dangsan.dotjoin.modules.toyproject.service.ToyProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/main")
public class MainController {

    private final ToyProjectService toyProjectService;
    private final MemoirService memoirService;
    private final QuestionService questionService;


    @GetMapping("/toyprojects")
    public ResponseEntity<?> inquireWorkingToyProjects() {
        List<InquireAllToyProjectDto> inquireAllToyProjectDtoList = toyProjectService.inquireWorkingToyProjects();
        return ResponseEntity.ok(inquireAllToyProjectDtoList);
    }

    @GetMapping("/memoirs")
    public ResponseEntity<?> inquireAllMemoir() {
        List<InquireAllMemoirDto> inquireAllMemoirDtoList = memoirService.inquireAllMemoir();

        return ResponseEntity.ok(inquireAllMemoirDtoList);
    }

    @GetMapping("/questions")
    public ResponseEntity<?> inquireAllQuestion() {
        List<InquireAllQuestionDto> inquireAllQuestionDtoList = questionService.inquireAllQuestion();

        return ResponseEntity.ok(inquireAllQuestionDtoList);
    }
}
