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

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToyProjectService {
    private final ToyProjectRepository toyProjectRepository;
    private final SubProjectRepository subProjectRepository;

    public InquireTargetToyProjectDto inquireTargetToyProject(Long projectId){
        ToyProject toyProject= toyProjectRepository.findById(projectId).get(); //.get()의 경우 결과값이 null일 경우 NoSuchElementException 발생

        InquireTargetToyProjectDto inquireTargetToyProjectDto= new InquireTargetToyProjectDto(toyProject);

        return inquireTargetToyProjectDto;

    }

    public List<InquireAllToyProjectDto> inquireAllToyProject(){

        return null;
    }

    public void registerToyProject(RegisterToyProjectDto registerToyProjectDto){
        ToyProject toyProject=new ToyProject(registerToyProjectDto);
        toyProjectRepository.save(toyProject);

    }

    public void updateTargetToyProject(Long projectId, UpdateTargetToyProjectDto targetToyProjectDto){
        ToyProject toyProject=toyProjectRepository.findById(projectId).get();
        toyProject.update(targetToyProjectDto);
        toyProjectRepository.save(toyProject);

    }

    public void deleteTargetToyProject(Long projectId){
        toyProjectRepository.deleteById(projectId);
    }

}
