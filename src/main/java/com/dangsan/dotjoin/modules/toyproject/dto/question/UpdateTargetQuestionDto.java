package com.dangsan.dotjoin.modules.toyproject.dto.question;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTargetQuestionDto {

    private String title;
    private String detail;

}
