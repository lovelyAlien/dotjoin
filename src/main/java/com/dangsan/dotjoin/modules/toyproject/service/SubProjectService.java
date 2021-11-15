package com.dangsan.dotjoin.modules.toyproject.service;

import com.dangsan.dotjoin.modules.toyproject.dto.subproject.InquireAllSubProjectDto;
import com.dangsan.dotjoin.modules.toyproject.dto.subproject.InquireTargetSubProjectDto;
import com.dangsan.dotjoin.modules.toyproject.dto.subproject.RegisterSubProjectDto;
import com.dangsan.dotjoin.modules.toyproject.dto.subproject.UpdateTargetSubProjectDto;
import com.dangsan.dotjoin.modules.toyproject.model.ToyProject;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.SubProject;
import com.dangsan.dotjoin.modules.toyproject.repository.ToyProjectRepository;
import com.dangsan.dotjoin.modules.toyproject.repository.subproject.SubProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class SubProjectService {

    private final SubProjectRepository subProjectRepository;
    private final ToyProjectRepository toyProjectRepository;

    public InquireTargetSubProjectDto inquireTargetSubProject(Long projectId, Long subProjectId){

        SubProject subProject=subProjectRepository.findById(subProjectId).get();
        InquireTargetSubProjectDto inquireTargetSubProjectDto=new InquireTargetSubProjectDto(projectId, subProject);

        return inquireTargetSubProjectDto;

    }

    public List<InquireAllSubProjectDto> inquireAllSubProjectInToyProject(Long toyProjectId){
        ToyProject toyProject= toyProjectRepository.findById(toyProjectId).get();

        List<InquireAllSubProjectDto> inquireAllSubProjectDtoList=new ArrayList<>();

        for(SubProject subProject: toyProject.getSubProjects()){
            InquireAllSubProjectDto inquireAllSubProjectDto=new InquireAllSubProjectDto(subProject);
            inquireAllSubProjectDtoList.add(inquireAllSubProjectDto);
        }

        return inquireAllSubProjectDtoList;
    }

    public void registerSubProject(Long projectId, RegisterSubProjectDto registerSubProjectDto){

        ToyProject toyProject=toyProjectRepository.findById(projectId).get();

        SubProject subProject= new SubProject(toyProject, registerSubProjectDto);

        subProjectRepository.save(subProject);

    }

    public void updateTargetSubProject(Long subProjectId, UpdateTargetSubProjectDto updateTargetSubProjectDto){

        SubProject subProject=subProjectRepository.findById(subProjectId).get();

        subProject.update(updateTargetSubProjectDto);
        subProjectRepository.save(subProject);
    }

    public void deleteTargetSubProject(Long subProjectId){
        subProjectRepository.deleteById(subProjectId);

    }


}
