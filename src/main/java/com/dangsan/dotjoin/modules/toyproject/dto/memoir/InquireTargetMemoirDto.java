package com.dangsan.dotjoin.modules.toyproject.dto.memoir;

import com.dangsan.dotjoin.modules.toyproject.model.subproject.Memoir;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.SubProject;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.Url;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InquireTargetMemoirDto {

    private Long memoirId;

    private Long subProjectId;

    private String title;

    private String whatDid;

    private String whatLearn;

    private String whatProblem;

    private String howSolution;

    private String whyReason;

    private List<UrlDto> urlDtoList=new ArrayList<>();

    private LocalDate developDate;

    private LocalDateTime insertDate;

    private LocalDateTime updateDate;

    private LocalDateTime deleteDate;

    private boolean deleteData = false;


    public InquireTargetMemoirDto(Memoir memoir){
        this.memoirId=memoir.getId();

        this.subProjectId=memoir.getSubProject().getId();

        this.title=memoir.getTitle();

        this.whatDid=memoir.getWhatDid();

        this.whatLearn=memoir.getWhatLearn();

        this.whatProblem=memoir.getWhatProblem();

        this.howSolution=memoir.getHowSolution();

        this.whyReason=memoir.getWhyReason();

        for(Url url: memoir.getUrl()){
            UrlDto urlDto=new UrlDto(url);
            this.urlDtoList.add(urlDto);
        }

        this.developDate=memoir.getDevelopDate();

        this.insertDate= memoir.getInsertDate();

        this.updateDate=memoir.getUpdateDate();

        this.deleteDate=memoir.getDeleteDate();

        this.deleteData=memoir.isDeleteData();


    }

}
