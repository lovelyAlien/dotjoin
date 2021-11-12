package com.dangsan.dotjoin.modules.toyproject.controller;

import com.dangsan.dotjoin.modules.toyproject.dto.memoir.InquireAllMemoir;
import com.dangsan.dotjoin.modules.toyproject.dto.memoir.InquireTargetMemoir;
import com.dangsan.dotjoin.modules.toyproject.dto.memoir.RegisterMemoir;
import com.dangsan.dotjoin.modules.toyproject.dto.memoir.UpdateTargetMemoir;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class MemoirController {

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
}
