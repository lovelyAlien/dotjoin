package com.dangsan.dotjoin.modules.toyproject.dto.toyproject;

import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
public class ProjectInWork {

    private String title;

    private String shotDescription;

    private String fullDescription;

    private String imagePath;

    private LocalDate projectStart;

    private LocalDate projectEnd;
}
