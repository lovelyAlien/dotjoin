package com.dangsan.dotjoin.modules.toyproject.controller;

import com.dangsan.dotjoin.modules.toyproject.dto.answer.InquireTargetAnswerDto;
import com.dangsan.dotjoin.modules.toyproject.dto.kanban.UpdateTargetCardDto;
import com.dangsan.dotjoin.modules.toyproject.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("/api/toyprojects/{projectId}/subprojects/{subProjectId}/questions/{questionId}")
public class AnswerController {

    private final AnswerService answerService;
    //CRUD of Answer
    @GetMapping("/answers/{answerId}")
    public ResponseEntity<?> inquireTargetAnswer (@PathVariable Long questionId, @PathVariable Long answerId) {

        InquireTargetAnswerDto inquireTargetAnswerDto= answerService.inquireTargetAnswer(questionId, answerId);

        return ResponseEntity.ok(inquireTargetAnswerDto);
    }

    @PostMapping("/answers")
    public ResponseEntity<?> registerAnswer (@PathVariable Long questionId, @AuthenticationPrincipal User user) {
        answerService.registerAnswer(questionId, user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/answers/{answerId}")
    public ResponseEntity<?> updateTargetAnswer (@PathVariable Long answerId, @RequestBody String detail) {

        answerService.updateTargetAnswer(answerId, detail);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/answers/{answerId}")
    public ResponseEntity<?> deleteTargetAnswer (@PathVariable Long answerId) {

        answerService.deleteTargetAnswer(answerId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
