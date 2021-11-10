package com.dangsan.dotjoin.modules.toyproject.dto.question;

import lombok.Data;

@Data
public class InquireAllQuestion {

    private Long questionId;

    private String questioner;

    private String title;

    private String content;

    private double rate;
}
