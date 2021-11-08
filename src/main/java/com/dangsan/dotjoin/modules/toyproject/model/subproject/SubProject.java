package com.dangsan.dotjoin.modules.toyproject.model.subproject;

import com.dangsan.dotjoin.modules.Timestamped;
import com.dangsan.dotjoin.modules.toyproject.dto.subproject.RegisterSubProject;
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

    @ManyToOne
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

    @OneToOne(mappedBy ="subProject")
    private KanbanBoard kanbanBoard;

    @OneToMany(mappedBy = "subProject")
    private List<SubProjectRate> projectRates = new ArrayList<SubProjectRate>();

    @OneToMany(mappedBy = "subProject")
    private List<Question> projectQuestions = new ArrayList<Question>();

    @OneToMany(mappedBy = "subProject")
    private List<Memoir> projectMemoirs = new ArrayList<Memoir>();

    public SubProject(ToyProject toyProject, RegisterSubProject registerDto){
        this.toyProject=toyProject;
        this.title=registerDto.getTitle();
        this.expectedWork=registerDto.getExpectedWork();
    }


    public void setKanbanBoard(ToyProject toyProject, KanbanBoard kanbanBoard){
        kanbanBoard.setToyProjectAndSubProject(toyProject, this);
        this.setKanbanBoard(kanbanBoard);
    }


}
