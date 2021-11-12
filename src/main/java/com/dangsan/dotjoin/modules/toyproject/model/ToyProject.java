package com.dangsan.dotjoin.modules.toyproject.model;

import com.dangsan.dotjoin.modules.account.model.Account;
import com.dangsan.dotjoin.modules.toyproject.dto.toyproject.RegisterToyProjectDto;
import com.dangsan.dotjoin.modules.toyproject.dto.toyproject.UpdateTargetToyProjectDto;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.SubProject;
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
public class ToyProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String shortDescription;

    @Column
    private String fullDescription;

    @Column
    private String imagePath;

    @Column
    private LocalDate projectStart;

    @Column
    private LocalDate projectEnd;

    @OneToMany(mappedBy = "toyProject")
    private List<SubProject> subProjects = new ArrayList<SubProject>();

    @OneToMany
    @JoinTable(
            name="toy_project_requester",
            inverseJoinColumns = @JoinColumn(name="requester_id"))
    private List<Account> requesters = new ArrayList<Account>(); //참여 희망자

    @OneToMany
    @JoinTable(name="toy_project_member",
            inverseJoinColumns = @JoinColumn(name="member_id"))
    private List<Account> members= new ArrayList<Account>(); //참여자

    @OneToMany(mappedBy = "toyProject")
    private List<ToyProjectRate> projectRates = new ArrayList<ToyProjectRate>();

    public ToyProject(RegisterToyProjectDto registerToyProjectDto){
        this.title=registerToyProjectDto.getTitle();
        this.shortDescription=registerToyProjectDto.getShortDescription();
        this.fullDescription=registerToyProjectDto.getFullDescription();
    }

    public void update(UpdateTargetToyProjectDto updateTargetToyProjectDto){

        this.title= updateTargetToyProjectDto.getTitle();
        this.shortDescription=updateTargetToyProjectDto.getShortDescription();
        this.fullDescription=updateTargetToyProjectDto.getFullDescription();
        this.projectStart=updateTargetToyProjectDto.getProjectStart();
        this.projectEnd=updateTargetToyProjectDto.getProjectEnd();
    }


}
