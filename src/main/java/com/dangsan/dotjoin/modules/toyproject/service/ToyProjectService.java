package com.dangsan.dotjoin.modules.toyproject.service;

import com.dangsan.dotjoin.modules.toyproject.dto.subproject.RegisterSubProjectDto;
import com.dangsan.dotjoin.modules.toyproject.dto.toyproject.InquireAllToyProjectDto;
import com.dangsan.dotjoin.modules.toyproject.dto.toyproject.InquireTargetToyProjectDto;
import com.dangsan.dotjoin.modules.toyproject.dto.toyproject.RegisterToyProjectDto;
import com.dangsan.dotjoin.modules.toyproject.dto.toyproject.UpdateTargetToyProjectDto;
import com.dangsan.dotjoin.modules.toyproject.model.ToyProject;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.SubProject;
import com.dangsan.dotjoin.modules.toyproject.repository.ToyProjectRepository;
import com.dangsan.dotjoin.modules.toyproject.repository.subproject.SubProjectRepository;
import com.dangsan.dotjoin.modules.toyproject.repository.subproject.kanbanboard.KanbanBoardRepository;
import com.dangsan.dotjoin.modules.toyproject.repository.subproject.kanbanboard.KanbanListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ToyProjectService {
    private final ToyProjectRepository toyProjectRepository;
    private final SubProjectRepository subProjectRepository;

    public InquireTargetToyProjectDto inquireTargetToyProject(Long projectId){
        System.out.println("projectId: "+ projectId);
        System.out.println("현 위치: ToyProjectService");

        ToyProject toyProject= toyProjectRepository.findById(projectId).get(); //.get()의 경우 결과값이 null일 경우 NoSuchElementException 발생

        System.out.println("Get Toyproject object");
        System.out.println(toyProject.toString());
        InquireTargetToyProjectDto inquireTargetToyProjectDto= new InquireTargetToyProjectDto(toyProject);

        return inquireTargetToyProjectDto;

    }

    public List<InquireAllToyProjectDto> inquireAllToyProject(){

        List<ToyProject> toyProjects= toyProjectRepository.findAll();
        List<InquireAllToyProjectDto> inquireAllToyProjectDtoList= new ArrayList<>();

        for(ToyProject toyProject: toyProjects){
            InquireAllToyProjectDto inquireAllToyProjectDto=new InquireAllToyProjectDto(toyProject);
            inquireAllToyProjectDtoList.add(inquireAllToyProjectDto);

        }
        return inquireAllToyProjectDtoList;
    }

    public void registerToyProject(RegisterToyProjectDto registerToyProjectDto){
        ToyProject toyProject=new ToyProject(registerToyProjectDto);
        toyProjectRepository.save(toyProject);

    }
    @Transactional
    public void updateTargetToyProject(Long projectId, UpdateTargetToyProjectDto targetToyProjectDto){
        ToyProject toyProject=toyProjectRepository.findById(projectId).get();
        toyProject.update(targetToyProjectDto);
        toyProjectRepository.save(toyProject);

    }
    @Transactional
    public void deleteTargetToyProject(Long projectId){
        toyProjectRepository.deleteById(projectId);
    }

}
