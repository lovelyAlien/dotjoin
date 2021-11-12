package com.dangsan.dotjoin.modules.toyproject.dto.toyproject;

import com.dangsan.dotjoin.modules.account.model.Account;
import com.dangsan.dotjoin.modules.toyproject.model.ToyProjectRate;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.SubProject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTargetToyProjectDto {



    private String title;


    private String shotDescription;


    private String fullDescription;



    private LocalDate projectStart;


    private LocalDate projectEnd;



}
