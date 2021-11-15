package com.dangsan.dotjoin.modules.toyproject.dto.toyproject;

import com.dangsan.dotjoin.modules.toyproject.model.ToyProject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InquireAllToyProjectDto {

    private Long toyProjectId;

    private String title;

    private String shortDescription;

    private String fullDescription;

    private String imagePath;

    private LocalDate projectStart;

    private LocalDate projectEnd;

    public InquireAllToyProjectDto(ToyProject toyProject) {
        this.toyProjectId=toyProject.getId();

        this.title=toyProject.getTitle();

        this.shortDescription=toyProject.getShortDescription();

        this.fullDescription=toyProject.getFullDescription();

        this.imagePath=toyProject.getImagePath();

        this.projectStart=toyProject.getProjectStart();

        this.projectEnd=toyProject.getProjectEnd();
    }

}
