package com.dangsan.dotjoin.modules.toyproject.controller;

import com.dangsan.dotjoin.modules.toyproject.dto.answer.InquireTargetAnswerDto;
import com.dangsan.dotjoin.modules.toyproject.dto.question.InquireAllQuestion;
import com.dangsan.dotjoin.modules.toyproject.dto.question.InquireTargetQuestionDto;
import com.dangsan.dotjoin.modules.toyproject.dto.question.UpdateTargetQuestionDto;
import com.dangsan.dotjoin.modules.toyproject.service.AnswerService;
import com.dangsan.dotjoin.modules.toyproject.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/toyprojects/{projectId}/subprojects/{subProjectId}")
public class QuestionController {
    private final QuestionService questionService;

    //CRUD of Question
    @GetMapping("/questions/{questionId}")
    public ResponseEntity<?> inquireTargetQuestion (@PathVariable Long subProjectId, @PathVariable Long questionId) {

        InquireTargetQuestionDto inquireTargetQuestionDto= questionService.inquireTargetQuestion(subProjectId, questionId);

        return ResponseEntity.ok(inquireTargetQuestionDto);
    }

    @GetMapping("/{projectId}/subprojects/{subprojectId}/questions/")
    public ResponseEntity<?> inquireAllQuestion (@PathVariable String projectId,
                                                 @PathVariable String subprojectId) {
        InquireAllQuestion allQuestion = new InquireAllQuestion();

        return ResponseEntity.ok(allQuestion);
    }

    @PostMapping("/questions")
    public ResponseEntity<?> registerQuestion (@PathVariable Long subProjectId, @AuthenticationPrincipal User user) {
        questionService.registerQuestion(subProjectId, user);
        return ResponseEntity.ok(HttpStatus.OK);
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
