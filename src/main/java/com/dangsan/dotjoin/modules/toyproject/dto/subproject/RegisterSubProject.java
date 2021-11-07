package com.dangsan.dotjoin.modules.toyproject.dto.subproject;

import com.dangsan.dotjoin.modules.toyproject.model.ToyProject;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.Memoir;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.Question;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.SubProjectRate;
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
public class RegisterSubProject {

    private String title;

    private String expectedWork; // 예정작업


}
