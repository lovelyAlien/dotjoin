package com.dangsan.dotjoin.modules.toyproject.dto.subproject;

import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
public class UpdateTargetSubProjectDto {

    private String title;

    private String expectedWork; // 예정작업

    private String realWork; // 실제 작업

    private LocalDate planStart;

    private LocalDate planEnd;

    private LocalDate realStart;

    private LocalDate realEnd;
}
