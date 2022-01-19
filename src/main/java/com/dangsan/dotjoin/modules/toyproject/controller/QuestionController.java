package com.dangsan.dotjoin.modules.toyproject.controller;

import com.dangsan.dotjoin.modules.toyproject.dto.question.InquireAllQuestionDto;
import com.dangsan.dotjoin.modules.toyproject.dto.question.InquireTargetQuestionDto;
import com.dangsan.dotjoin.modules.toyproject.dto.question.UpdateTargetQuestionDto;
import com.dangsan.dotjoin.modules.toyproject.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/toyprojects/{projectId}/subprojects/{subProjectId}")
public class QuestionController {
    private final QuestionService questionService;

    //CRUD of Question
    @GetMapping("/questions/{questionId}")
    public ResponseEntity<?> inquireTargetQuestion (@PathVariable Long subProjectId, @PathVariable Long questionId) {

        InquireTargetQuestionDto inquireTargetQuestionDto= questionService.inquireTargetQuestion(subProjectId, questionId);

        return ResponseEntity.ok(inquireTargetQuestionDto);
    }

    @GetMapping("/questions")
    public ResponseEntity<?> inquireAllQuestionInSubProject (@PathVariable Long subProjectId) {
        List<InquireAllQuestionDto> inquireAllQuestionDtoList = questionService.inquireAllQuestionInSubProject(subProjectId);

        return ResponseEntity.ok(inquireAllQuestionDtoList);
    }

    @PostMapping("/questions")
    public ResponseEntity<?> registerQuestion (@PathVariable Long subProjectId, @AuthenticationPrincipal User user) {
        Long questionId= questionService.registerQuestion(subProjectId, user);
        return ResponseEntity.ok(questionId);
    }


    @PutMapping("/questions/{questionId}")
    public ResponseEntity<?> updateTargetQuestion (@PathVariable Long questionId, @RequestBody UpdateTargetQuestionDto updateTargetQuestionDto) {

        questionService.updateTargetQuestion(questionId, updateTargetQuestionDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/questions/{questionId}")
    public ResponseEntity<?> deleteTargetQuestion (@PathVariable Long questionId) {

        questionService.deleteTargetQuestion(questionId);
        return ResponseEntity.ok(HttpStatus.OK);
    }




}
