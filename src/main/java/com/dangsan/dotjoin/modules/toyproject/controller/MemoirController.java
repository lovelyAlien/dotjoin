package com.dangsan.dotjoin.modules.toyproject.controller;

import com.dangsan.dotjoin.modules.toyproject.dto.memoir.InquireAllMemoirDto;
import com.dangsan.dotjoin.modules.toyproject.dto.memoir.InquireTargetMemoirDto;
import com.dangsan.dotjoin.modules.toyproject.dto.memoir.RegisterMemoirDto;
import com.dangsan.dotjoin.modules.toyproject.dto.memoir.UpdateTargetMemoirDto;
import com.dangsan.dotjoin.modules.toyproject.service.MemoirService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/toyprojects/{projectId}/subprojects/{subProjectId}")
public class MemoirController {
    private final MemoirService memoirService;

    @PostMapping("/memoirs")
    public ResponseEntity<?> registerMemoir (@PathVariable Long subProjectId, @RequestBody String title) {

        Long memoirId= memoirService.registerMemoir(subProjectId, title);

        return ResponseEntity.ok(memoirId);
    }

    @GetMapping("/memoirs")
    public ResponseEntity<?> inquireAllMemoir (@PathVariable Long subprojectId) {


        List<InquireAllMemoirDto> inquireAllMemoirDtoList = memoirService.inquireAllMemoir(subprojectId);

        return ResponseEntity.ok(inquireAllMemoirDtoList);
    }

    @GetMapping("/memoirs/{memoirId}")
    public ResponseEntity<?> inquireTargetMemoir(@PathVariable Long memoirId) {

        InquireTargetMemoirDto inquireTargetMemoirDto= memoirService.inquireTargetMemoir(memoirId);
        return ResponseEntity.ok(inquireTargetMemoirDto);
    }

    @PutMapping("/memoirs/{memoirId}")
    public ResponseEntity<?> updateTargetMemoir(@PathVariable Long memoirId,
                                                @RequestBody UpdateTargetMemoirDto updateTargetMemoirDto) {

        memoirService.updateTargetMemoir(memoirId, updateTargetMemoirDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/memoirs/{memoirId}")
    public ResponseEntity<?> deleteTargetMemoir(@PathVariable Long memoirId) {
        memoirService.deleteTargetMemoir(memoirId);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
