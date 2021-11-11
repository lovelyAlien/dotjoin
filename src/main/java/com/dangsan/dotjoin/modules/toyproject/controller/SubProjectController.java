package com.dangsan.dotjoin.modules.toyproject.controller;



import com.dangsan.dotjoin.modules.toyproject.dto.subproject.RegisterSubProject;
import com.dangsan.dotjoin.modules.toyproject.service.ToyProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/toyprojects/{projectId}/subprojects")
public class SubProjectController {

    private final ToyProjectService toyProjectService;

    // region 단위 프로젝트 (SubProject)
    @PostMapping("/")
    public ResponseEntity<?> registerSubProject (@PathVariable Long projectId,
                                                 @RequestBody RegisterSubProject subProjectDto) {

        toyProjectService.registerSubProject(projectId, subProjectDto);

        return ResponseEntity.ok(HttpStatus.OK);
    }
    //END_POINT





}
