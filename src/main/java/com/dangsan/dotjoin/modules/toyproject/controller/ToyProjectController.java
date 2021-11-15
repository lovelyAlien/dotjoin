package com.dangsan.dotjoin.modules.toyproject.controller;

import com.dangsan.dotjoin.modules.toyproject.dto.toyproject.InquireAllToyProjectDto;
import com.dangsan.dotjoin.modules.toyproject.dto.toyproject.InquireTargetToyProjectDto;
import com.dangsan.dotjoin.modules.toyproject.dto.toyproject.RegisterToyProjectDto;
import com.dangsan.dotjoin.modules.toyproject.dto.toyproject.UpdateTargetToyProjectDto;
import com.dangsan.dotjoin.modules.toyproject.service.ToyProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/toyprojects")
public class ToyProjectController {
    private final ToyProjectService toyProjectService;

    @GetMapping("/{projectId}")
    public ResponseEntity<?> inquireTargetToyProject(@PathVariable Long projectId) {

        System.out.println("현 위치: ToyProjectController");

        InquireTargetToyProjectDto inquireTargetToyProjectDto= toyProjectService.inquireTargetToyProject(projectId);

        return ResponseEntity.ok(inquireTargetToyProjectDto);
    }

    @GetMapping
    public ResponseEntity<?> inquireAllToyProject() {
        List<InquireAllToyProjectDto> inquireAllToyProjectDtoList= toyProjectService.inquireAllToyProject();

        return ResponseEntity.ok(inquireAllToyProjectDtoList);
    }

    @PostMapping
    public ResponseEntity<?> registerToyProject(@RequestBody RegisterToyProjectDto registerToyProjectDto) {
        toyProjectService.registerToyProject(registerToyProjectDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<?> updateTargetToyProject(@PathVariable Long projectId,
                                                    @RequestBody UpdateTargetToyProjectDto targetToyProjectDto) {

        toyProjectService.updateTargetToyProject(projectId, targetToyProjectDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteTargetToyProject (@PathVariable Long projectId) {

        toyProjectService.deleteTargetToyProject(projectId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
