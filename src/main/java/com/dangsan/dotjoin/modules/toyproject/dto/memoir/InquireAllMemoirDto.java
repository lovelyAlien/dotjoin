package com.dangsan.dotjoin.modules.toyproject.dto.memoir;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InquireAllMemoirDto {

    private Long subProjectId;

    private List<MemoirDto> memoirDtoList=new ArrayList<>();
}
