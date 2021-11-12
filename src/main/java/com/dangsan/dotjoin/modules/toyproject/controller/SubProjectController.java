package com.dangsan.dotjoin.modules.toyproject.controller;



import com.dangsan.dotjoin.modules.toyproject.dto.question.UpdateTargetQuestionDto;
import com.dangsan.dotjoin.modules.toyproject.dto.subproject.InquireAllSubProject;
import com.dangsan.dotjoin.modules.toyproject.dto.subproject.InquireTargetSubProjectDto;
import com.dangsan.dotjoin.modules.toyproject.dto.subproject.RegisterSubProjectDto;
import com.dangsan.dotjoin.modules.toyproject.dto.subproject.UpdateTargetSubProjectDto;
import com.dangsan.dotjoin.modules.toyproject.service.SubProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/toyprojects/{projectId}")
public class SubProjectController {
    private final SubProjectService subProjectService;

    @GetMapping("/subprojects/{subProjectId}")
    public ResponseEntity<?> inquireTargetSubProject (@PathVariable Long projectId, @PathVariable Long subProjectId) {

        InquireTargetSubProjectDto inquireTargetSubProjectDto= subProjectService.inquireTargetSubProject(projectId, subProjectId);

        return ResponseEntity.ok(inquireTargetSubProjectDto);
    }

    @GetMapping("/subprojects/")
    public ResponseEntity<?> inquireAllSubProject (@PathVariable String projectId) {
        InquireAllSubProject allSubProject = new InquireAllSubProject();

        return ResponseEntity.ok(allSubProject);
    }



    @PostMapping("/subprojects")
    public ResponseEntity<?> registerSubProject (@PathVariable Long projectId,
                                                 @RequestBody RegisterSubProjectDto registerSubProjectDto) {

        subProjectService.registerSubProject(projectId, registerSubProjectDto);

        return ResponseEntity.ok(HttpStatus.OK);
    }


    @PutMapping("/subprojects/{subProjectId}")
    public ResponseEntity<?> updateTargetQuestion (@PathVariable Long subProjectId, @RequestBody UpdateTargetSubProjectDto updateTargetSubProjectDto) {

        subProjectService.updateTargetSubProject(subProjectId, updateTargetSubProjectDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/subprojects/{subProjectId}")
    public ResponseEntity<?> deleteTargetQuestion (@PathVariable Long subProjectId) {

        subProjectService.deleteTargetSubProject(subProjectId);
        return ResponseEntity.ok(HttpStatus.OK);
    }




}
