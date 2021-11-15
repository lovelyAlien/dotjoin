package com.dangsan.dotjoin.modules.toyproject.dto.subproject;


import com.dangsan.dotjoin.modules.toyproject.model.ToyProject;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.SubProject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubProjectDto {

    private Long subProjectId;

    private String title;


    private String expectedWork; // 예정작업


    private String realWork; // 실제 작업


    public SubProjectDto(SubProject subProject){
        this.subProjectId=subProject.getId();
        this.title=subProject.getTitle();
        this.expectedWork=subProject.getExpectedWork();
        this.realWork=subProject.getRealWork();
    }


}
