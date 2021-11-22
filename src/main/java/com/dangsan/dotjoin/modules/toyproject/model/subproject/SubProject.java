package com.dangsan.dotjoin.modules.toyproject.model.subproject;

import com.dangsan.dotjoin.modules.Timestamped;
import com.dangsan.dotjoin.modules.toyproject.dto.subproject.RegisterSubProjectDto;
import com.dangsan.dotjoin.modules.toyproject.dto.subproject.UpdateTargetSubProjectDto;
import com.dangsan.dotjoin.modules.toyproject.model.ToyProject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class SubProject extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private ToyProject toyProject;

    @Column
    private String title;

    @Column
    private String expectedWork; // 예정작업

    @Column
    private String realWork; // 실제 작업

    @Column
    private LocalDate planStart;

    @Column
    private LocalDate planEnd;

    @Column
    private LocalDate realStart;

    @Column
    private LocalDate realEnd;

//    @OneToOne(mappedBy ="subProject", fetch = FetchType.LAZY, orphanRemoval = true)
//    private KanbanBoard kanbanBoard;

    @OneToMany(mappedBy = "subProject", orphanRemoval = true)
    private List<SubProjectRate> projectRates = new ArrayList<SubProjectRate>();

    @OneToMany(mappedBy = "subProject", orphanRemoval = true)
    private List<Question> projectQuestions = new ArrayList<Question>();

    @OneToMany(mappedBy = "subProject", orphanRemoval = true)
    private List<Memoir> projectMemoirs = new ArrayList<Memoir>();

    public SubProject(ToyProject toyProject, RegisterSubProjectDto registerDto){
        this.toyProject=toyProject;
        this.title=registerDto.getTitle();
        this.expectedWork=registerDto.getExpectedWork();
    }

    public void update(UpdateTargetSubProjectDto updateTargetSubProjectDto){
        this.title=updateTargetSubProjectDto.getTitle();
        this.expectedWork=updateTargetSubProjectDto.getExpectedWork();
        this.realWork=updateTargetSubProjectDto.getRealWork();
        this.planStart=updateTargetSubProjectDto.getPlanStart();
        this.planEnd=updateTargetSubProjectDto.getPlanEnd();
        this.realStart=updateTargetSubProjectDto.getRealStart();
        this.realEnd=updateTargetSubProjectDto.getRealEnd();

    }


}
