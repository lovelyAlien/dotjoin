package com.dangsan.dotjoin.modules.toyproject.dto.toyproject;

import com.dangsan.dotjoin.modules.account.model.Account;
import com.dangsan.dotjoin.modules.toyproject.model.ToyProject;
import com.dangsan.dotjoin.modules.toyproject.model.ToyProjectRate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToyProjectRateDto {

    private Long toyProjectRateId;

    private Long raterId;

    private String raterNickname;

    private double rateScore;

    public ToyProjectRateDto(ToyProjectRate toyProjectRate){
        this.toyProjectRateId=toyProjectRate.getId();

        this.raterId=toyProjectRate.getRater().getId();

        this.raterNickname=toyProjectRate.getRater().getNickname();

        this.rateScore=toyProjectRate.getRateScore();
    }

}
