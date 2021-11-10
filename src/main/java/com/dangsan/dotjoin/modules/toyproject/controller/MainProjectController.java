package com.dangsan.dotjoin.modules.toyproject.controller;

import com.dangsan.dotjoin.modules.toyproject.dto.memoir.InquireAllMemoir;
import com.dangsan.dotjoin.modules.toyproject.dto.question.InquireAllQuestion;
import com.dangsan.dotjoin.modules.toyproject.dto.toyproject.ToyProjectSimple;
import com.dangsan.dotjoin.modules.toyproject.service.ToyProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/main")
public class MainProjectController {

    private final ToyProjectService toyProjectService;

    @GetMapping("/projectinwork")
    public ResponseEntity<?> currentWorkingProject() {
        List<ToyProjectSimple> findProject = toyProjectService.findMyProject();
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/memoirs")
    public ResponseEntity<?> getAllMemoirs() {
        List<InquireAllMemoir> memoirs = toyProjectService.findAllMemoirs();

        return ResponseEntity.ok(memoirs);
    }

    @GetMapping("/qna")
    public ResponseEntity<?> getAllQuestions() {
        List<InquireAllQuestion> questions = toyProjectService.findAllQuestions();

        return ResponseEntity.ok(questions);
    }
}
