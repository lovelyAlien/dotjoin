package com.dangsan.dotjoin.modules.posting.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class WritePostingForm {

    @NotBlank
    private String contents;
}
