package com.dangsan.dotjoin.modules.toyproject.model.subproject;

import com.dangsan.dotjoin.modules.toyproject.dto.memoir.UpdateTargetMemoirDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Memoir {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private SubProject subProject;

    @Column
    private String title;

    @Column
    private String whatDid;

    @Column
    private String whatLearn;

    @Column
    private String whatProblem;

    @Column
    private String howSolution;

    @Column
    private String whyReason;

    @OneToMany(mappedBy = "memoir", orphanRemoval = true)
    private List<Url> url;

    @Column
    private LocalDate developDate;

    @Column
    private LocalDateTime insertDate;

    @Column
    private LocalDateTime updateDate;

    @Column
    private LocalDateTime deleteDate;

    @Column
    private boolean deleteData = false;

    public Memoir(SubProject subProject, String title) {
        this.subProject = subProject;
        this.title = title;
    }

    public void update(UpdateTargetMemoirDto updateTargetMemoirDto) {


        this.title = updateTargetMemoirDto.getTitle();

        this.whatDid = updateTargetMemoirDto.getWhatDid();

        this.whatLearn = updateTargetMemoirDto.getWhatLearn();

        this.whatProblem = updateTargetMemoirDto.getWhatProblem();

        this.howSolution = updateTargetMemoirDto.getHowSolution();

        this.whyReason = updateTargetMemoirDto.getWhyReason();

        this.developDate = updateTargetMemoirDto.getDevelopDate();

        this.insertDate = updateTargetMemoirDto.getInsertDate();

        this.updateDate = updateTargetMemoirDto.getUpdateDate();

        this.deleteDate = updateTargetMemoirDto.getDeleteDate();

        this.deleteData = updateTargetMemoirDto.isDeleteData();
    }


}
