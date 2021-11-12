package com.dangsan.dotjoin.modules.toyproject.dto.toyproject;

import com.dangsan.dotjoin.modules.account.model.Account;
import com.dangsan.dotjoin.modules.toyproject.dto.subproject.SubProjectDto;
import com.dangsan.dotjoin.modules.toyproject.model.ToyProject;
import com.dangsan.dotjoin.modules.toyproject.model.ToyProjectRate;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.SubProject;
import com.dangsan.dotjoin.modules.toyproject.repository.ToyProjectRepository;
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
public class InquireTargetToyProjectDto {

    private Long toyProjectId;

    private String title;

    private String shotDescription;

    private String fullDescription;

    private LocalDate projectStart;

    private LocalDate projectEnd;

    private List<SubProjectDto> subProjectDtoList = new ArrayList<SubProjectDto>();

    private List<RequesterDto> requesterDtoList = new ArrayList<RequesterDto>(); //참여 희망자

    private List<MemberDto> memberDtoList= new ArrayList<MemberDto>(); //참여자

    private List<ToyProjectRateDto> projectRates = new ArrayList<ToyProjectRateDto>();

    public InquireTargetToyProjectDto(ToyProject toyProject){
        this.toyProjectId=toyProject.getId();

        this.title=toyProject.getTitle();

        this.shotDescription=toyProject.getShotDescription();

        this.fullDescription=toyProject.getFullDescription();

        this.projectStart=toyProject.getProjectStart();

        this.projectEnd=toyProject.getProjectEnd();

        for(SubProject subProject: toyProject.getSubProjects()){
            SubProjectDto subProjectDto=new SubProjectDto(subProject);

            subProjectDtoList.add(subProjectDto);
        }

        for(Account requester: toyProject.getRequesters()){
            RequesterDto requesterDto= new RequesterDto(requester);
            requesterDtoList.add(requesterDto);
        }

        for(Account member:toyProject.getMembers()){
            MemberDto memberDto=new MemberDto(member);
            memberDtoList.add(memberDto);
        }

        for(ToyProjectRate toyProjectRate: toyProject.getProjectRates()){

            ToyProjectRateDto toyProjectRateDto=new ToyProjectRateDto(toyProjectRate);
            projectRates.add(toyProjectRateDto);
        }





    }



}
