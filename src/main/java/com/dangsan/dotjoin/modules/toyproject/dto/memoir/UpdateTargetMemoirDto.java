package com.dangsan.dotjoin.modules.toyproject.dto.memoir;

import com.dangsan.dotjoin.modules.toyproject.model.subproject.Url;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class UpdateTargetMemoirDto {

    private String title;

    private String whatDid;

    private String whatLearn;

    private String whatProblem;

    private String howSolution;

    private String whyReason;

    private List<Url> url;

    private LocalDate developDate;

    private LocalDateTime insertDate;

    private LocalDateTime updateDate;

    private LocalDateTime deleteDate;

    private boolean deleteData = false;
}
