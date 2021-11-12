package com.dangsan.dotjoin.modules.toyproject.controller;

import com.dangsan.dotjoin.modules.toyproject.dto.memoir.InquireAllMemoirDto;
import com.dangsan.dotjoin.modules.toyproject.dto.memoir.InquireTargetMemoirDto;
import com.dangsan.dotjoin.modules.toyproject.dto.memoir.RegisterMemoirDto;
import com.dangsan.dotjoin.modules.toyproject.dto.memoir.UpdateTargetMemoirDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class MemoirController {

    @PostMapping("/{projectId}/subprojects/{subprojectId}/memoirs/")
    public ResponseEntity<?> registerMemoir (@PathVariable String projectId,
                                             @PathVariable String subprojectId,
                                             @RequestBody RegisterMemoirDto memoir) {
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{projectId}/subprojects/{subprojectId}/memoirs/")
    public ResponseEntity<?> inquireAllMemoir (@PathVariable String projectId,
                                               @PathVariable String subprojectId) {
        InquireAllMemoirDto allMemoir = new InquireAllMemoirDto();

        return ResponseEntity.ok(allMemoir);
    }

    @GetMapping("/{projectId}/subprojects/{subprojectId}/memoirs/{memoirId}")
    public ResponseEntity<?> inquireTargetMemoir(@PathVariable String projectId,
                                                 @PathVariable String subprojectId,
                                                 @PathVariable String memoirId) {
        InquireTargetMemoirDto targetMemoir = new InquireTargetMemoirDto();

        return ResponseEntity.ok(targetMemoir);
    }

    @PutMapping("/{projectId}/subprojects/{subprojectId}/memoirs/{memoirId}")
    public ResponseEntity<?> updateTargetMemoir(@PathVariable String projectId,
                                                @PathVariable String subprojectId,
                                                @PathVariable String memoirId,
                                                @RequestBody UpdateTargetMemoirDto targetMemoir) {
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
