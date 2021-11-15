package com.dangsan.dotjoin.modules.toyproject.dto.subproject;

import com.dangsan.dotjoin.modules.toyproject.model.ToyProject;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.SubProject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InquireAllSubProjectDto {

    private Long subProjectId;

    private String title;

    private String expectedWork; // 예정작업

    private String realWork; // 실제 작업

    private LocalDate planStart;

    private LocalDate planEnd;

    private LocalDate realStart;

    private LocalDate realEnd;

    public InquireAllSubProjectDto(SubProject subProject) {
        this.subProjectId=subProject.getId();

        this.title=subProject.getTitle();

        this.expectedWork=subProject.getExpectedWork(); // 예정작업

        this.realWork=subProject.getRealWork(); // 실제 작업

        this.planStart=subProject.getPlanStart();

        this.planEnd=subProject.getPlanEnd();

        this.realStart=subProject.getRealStart();

        this.realEnd=subProject.getRealEnd();
    }
}
