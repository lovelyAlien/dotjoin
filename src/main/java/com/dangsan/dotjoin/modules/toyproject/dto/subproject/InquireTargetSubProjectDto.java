package com.dangsan.dotjoin.modules.toyproject.dto.subproject;

import com.dangsan.dotjoin.modules.toyproject.model.subproject.SubProject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InquireTargetSubProjectDto {
    private Long toyProjectId;


    private String title;


    private String expectedWork; // 예정작업


    private String realWork; // 실제 작업


    private LocalDate planStart;


    private LocalDate planEnd;


    private LocalDate realStart;


    private LocalDate realEnd;

    public InquireTargetSubProjectDto(Long toyProjectId, SubProject subProject){
        this.toyProjectId=toyProjectId;
        this.title=subProject.getTitle();
        this.expectedWork=subProject.getExpectedWork();
        this.realWork=subProject.getRealWork();
        this.planStart=subProject.getPlanStart();
        this.planEnd=subProject.getPlanEnd();
        this.realStart=subProject.getRealStart();
        this.realEnd=subProject.getRealEnd();
    }


}
