package com.dangsan.dotjoin.modules.toyproject.controller;

import com.dangsan.dotjoin.modules.toyproject.dto.memoir.*;
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
@RequestMapping("/toyprojects/{projectId}/subprojects/{subProjectId}")
public class MemoirController {
    private final MemoirService memoirService;

    @PostMapping("/memoirs")
    public ResponseEntity<?> registerMemoir (@PathVariable Long subProjectId, @RequestBody String title) {

        Long memoirId= memoirService.registerMemoir(subProjectId, title);

        return ResponseEntity.ok(memoirId);
    }

    @GetMapping("/memoirs")
    public ResponseEntity<?> inquireAllMemoirInSubProject (@PathVariable Long subProjectId) {


        List<InquireAllMemoirDto> inquireAllMemoirDtoList = memoirService.inquireAllMemoirInSubProject(subProjectId);

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


    //Url 생성
    @PostMapping("/memoirs/{memoirId}/urls")
    public ResponseEntity<?> registerUrl(@PathVariable Long memoirId, @RequestBody RegisterUrlDto registerUrlDto){
        Long urlId= memoirService.registerUrl(memoirId, registerUrlDto);

        return ResponseEntity.ok(urlId);
    }


    @PutMapping("/memoirs/{memoirId}/urls")
    public ResponseEntity<?> updateAllUrl(@PathVariable Long memoirId, @RequestBody List<UpdateTargetUrlDto> updateTargetUrlDtoList){

        memoirService.updateAllUrl(memoirId, updateTargetUrlDtoList);
        return ResponseEntity.ok(HttpStatus.OK);
    }


}
