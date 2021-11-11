package com.dangsan.dotjoin.modules.toyproject.controller;

import com.dangsan.dotjoin.modules.toyproject.dto.memoir.InquireAllMemoir;
import com.dangsan.dotjoin.modules.toyproject.dto.memoir.InquireTargetMemoir;
import com.dangsan.dotjoin.modules.toyproject.dto.memoir.RegisterMemoir;
import com.dangsan.dotjoin.modules.toyproject.dto.memoir.UpdateTargetMemoir;
import com.dangsan.dotjoin.modules.toyproject.dto.question.InquireAllQuestion;
import com.dangsan.dotjoin.modules.toyproject.dto.question.InquireTargetQuestionDto;
import com.dangsan.dotjoin.modules.toyproject.dto.question.RegisterQuestion;
import com.dangsan.dotjoin.modules.toyproject.dto.question.UpdateTargetQuestionDto;
import com.dangsan.dotjoin.modules.toyproject.dto.subproject.InquireAllSubProject;
import com.dangsan.dotjoin.modules.toyproject.dto.subproject.InquireTargetSubProject;
import com.dangsan.dotjoin.modules.toyproject.dto.toyproject.InquireAllToyProject;
import com.dangsan.dotjoin.modules.toyproject.dto.toyproject.InquireTargetToyProject;
import com.dangsan.dotjoin.modules.toyproject.dto.toyproject.RegisterToyProject;
import com.dangsan.dotjoin.modules.toyproject.dto.toyproject.UpdateTargetToyProject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/toyprojects")
public class ToyProjectController {

    // region 프로젝트 (ToyProject)
    @PostMapping("/")
    public ResponseEntity<?> registerToyProject(@RequestBody RegisterToyProject registerToyProject) {
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<?> inquireAllToyProject() {
        InquireAllToyProject allToyProject = new InquireAllToyProject();

        return ResponseEntity.ok(allToyProject);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> inquireTargetToyProject(@PathVariable String projectId) {
        InquireTargetToyProject targetToyProject = new InquireTargetToyProject();

        return ResponseEntity.ok(targetToyProject);
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<?> updateTargetToyProject(@PathVariable String projectId,
                                                    @RequestBody UpdateTargetToyProject targetToyProject) {
        return ResponseEntity.ok(HttpStatus.OK);
    }
    // endregion





    @GetMapping("/{projectId}/subprojects/")
    public ResponseEntity<?> inquireAllSubProject (@PathVariable String projectId) {
        InquireAllSubProject allSubProject = new InquireAllSubProject();

        return ResponseEntity.ok(allSubProject);
    }




    @GetMapping("/{projectId}/subprojects/{subprojectId}")
    public ResponseEntity<?> inquireTargetSubProject(@PathVariable String projectId,
                                                     @PathVariable String subprojectId) {
        InquireTargetSubProject targetSubProject = new InquireTargetSubProject();

        return ResponseEntity.ok(targetSubProject);
    }

    @PutMapping("/{projectId}/subprojects/{subprojectId}")
    public ResponseEntity<?> updateTargetSubProject(@PathVariable String projectId,
                                                    @PathVariable String subprojectId,
                                                    @RequestBody UpdateTargetToyProject targetSubProject) {
        return ResponseEntity.ok(HttpStatus.OK);
    }
    // endregion

    // region 단위 프로젝트 회고록 (SubProject Memoir)
    @PostMapping("/{projectId}/subprojects/{subprojectId}/memoirs/")
    public ResponseEntity<?> registerMemoir (@PathVariable String projectId,
                                             @PathVariable String subprojectId,
                                             @RequestBody RegisterMemoir memoir) {
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{projectId}/subprojects/{subprojectId}/memoirs/")
    public ResponseEntity<?> inquireAllMemoir (@PathVariable String projectId,
                                               @PathVariable String subprojectId) {
        InquireAllMemoir allMemoir = new InquireAllMemoir();

        return ResponseEntity.ok(allMemoir);
    }

    @GetMapping("/{projectId}/subprojects/{subprojectId}/memoirs/{memoirId}")
    public ResponseEntity<?> inquireTargetMemoir(@PathVariable String projectId,
                                                 @PathVariable String subprojectId,
                                                 @PathVariable String memoirId) {
        InquireTargetMemoir targetMemoir = new InquireTargetMemoir();

        return ResponseEntity.ok(targetMemoir);
    }

    @PutMapping("/{projectId}/subprojects/{subprojectId}/memoirs/{memoirId}")
    public ResponseEntity<?> updateTargetMemoir(@PathVariable String projectId,
                                                @PathVariable String subprojectId,
                                                @PathVariable String memoirId,
                                                @RequestBody UpdateTargetMemoir targetMemoir) {
        return ResponseEntity.ok(HttpStatus.OK);
    }
    // endregion

    // region 단위 프로젝트 Question (SubProject Question)
    @PostMapping("/{projectId}/subprojects/{subprojectId}/questions/")
    public ResponseEntity<?> registerQuestion (@PathVariable String projectId,
                                             @PathVariable String subprojectId,
                                             @RequestBody RegisterQuestion question) {
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{projectId}/subprojects/{subprojectId}/questions/")
    public ResponseEntity<?> inquireAllQuestion (@PathVariable String projectId,
                                               @PathVariable String subprojectId) {
        InquireAllQuestion allQuestion = new InquireAllQuestion();

        return ResponseEntity.ok(allQuestion);
    }

    @GetMapping("/{projectId}/subprojects/{subprojectId}/questions/{questionId}")
    public ResponseEntity<?> inquireTargetQuestion(@PathVariable String projectId,
                                                 @PathVariable String subprojectId,
                                                 @PathVariable String questionId) {
        InquireTargetQuestionDto targetQuestion = new InquireTargetQuestionDto();

        return ResponseEntity.ok(targetQuestion);
    }

    @PutMapping("/{projectId}/subprojects/{subprojectId}/questions/{questionId}")
    public ResponseEntity<?> updateTargetQuestion(@PathVariable String projectId,
                                                @PathVariable String subprojectId,
                                                @PathVariable String questionId,
                                                @RequestBody UpdateTargetQuestionDto targetQuestion) {
        return ResponseEntity.ok(HttpStatus.OK);
    }
    // endregion
}
