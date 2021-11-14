package com.dangsan.dotjoin.modules.toyproject.dto.memoir;

import com.dangsan.dotjoin.modules.toyproject.model.subproject.Memoir;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InquireAllMemoirDto {

    private Long memoirId;

    private String title;

    private LocalDate developDate;

    private LocalDateTime insertDate;

    private LocalDateTime updateDate;

    private LocalDateTime deleteDate;

    private boolean deleteData = false;

    public InquireAllMemoirDto(Memoir memoir){

        this.memoirId=memoir.getId();

        this.title=memoir.getTitle();

        this.developDate=memoir.getDevelopDate();

        this.insertDate=memoir.getInsertDate();

        this.updateDate=memoir.getUpdateDate();

        this.deleteDate=memoir.getDeleteDate();

        this.deleteData=memoir.isDeleteData();
    }

}
