package com.dangsan.dotjoin.modules.toyproject.dto.memoir;

import lombok.Data;

import java.util.List;

@Data
public class InquireAllMemoir {

    private Long memoirId;

    private String projectName;

    private String subProjectName;

    private String title;

    private String whatDid;

    private String whatLearn;

    private String whatProblem;

    private String howSolution;

    private String whyReason;

    private List<String> referenceUrls;

    private List<String> gitUrls;
}
