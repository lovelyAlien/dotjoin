package com.dangsan.dotjoin.modules.toyproject.controller;



import com.dangsan.dotjoin.modules.toyproject.dto.question.QuestionDto;
import com.dangsan.dotjoin.modules.toyproject.dto.subproject.RegisterSubProject;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.Memoir;
import com.dangsan.dotjoin.modules.toyproject.service.QuestionService;
import com.dangsan.dotjoin.modules.toyproject.service.ToyProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/toyprojects/{projectId}/subprojects")
public class SubProjectController {
    private final QuestionService questionService;
    private final ToyProjectService toyProjectService;

    // region 단위 프로젝트 (SubProject)
    @PostMapping("/")
    public ResponseEntity<?> registerSubProject (@PathVariable Long projectId,
                                                 @RequestBody RegisterSubProject subProjectDto) {

        toyProjectService.registerSubProject(projectId, subProjectDto);

        return ResponseEntity.ok(HttpStatus.OK);
    }

//    @GetMapping("/{subProjectId}")
//    public ResponseEntity<?> inquireAllMemoirDto (@PathVariable Long subProjectId) {
//
//
//        return ResponseEntity.ok(null);
//    }
//
//    @GetMapping("/{subProjectId}")
//    public ResponseEntity<?> inquireAllQuestionDto (@PathVariable Long subProjectId) {
//
//
//        List<QuestionDto> questionDtoList= questionService.inquireAllQuestionDto(subProjectId);
//        return ResponseEntity.ok(questionDtoList);
//    }




}
