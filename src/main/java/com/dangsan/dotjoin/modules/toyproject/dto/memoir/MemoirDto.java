package com.dangsan.dotjoin.modules.toyproject.dto.memoir;


import com.dangsan.dotjoin.modules.toyproject.model.subproject.Memoir;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemoirDto {

    private Long memoirId;

    private String title;

    private LocalDate developDate;

    private LocalDateTime insertDate;

    private LocalDateTime updateDate;

    private LocalDateTime deleteDate;

    private boolean deleteData = false;

    public MemoirDto(Memoir memoir){
        this.memoirId=memoir.getId();

        this.title=memoir.getTitle();

        this.developDate=memoir.getDevelopDate();

        this.insertDate=memoir.getInsertDate();

        this.updateDate=memoir.getUpdateDate();

        this.deleteDate=memoir.getDeleteDate();

        this.deleteData=memoir.isDeleteData();
    }
}
