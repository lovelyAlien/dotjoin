package com.dangsan.dotjoin.modules.toyproject.service;

import com.dangsan.dotjoin.modules.toyproject.dto.subproject.RegisterSubProjectDto;
import com.dangsan.dotjoin.modules.toyproject.model.ToyProject;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.SubProject;
import com.dangsan.dotjoin.modules.toyproject.repository.ToyProjectRepository;
import com.dangsan.dotjoin.modules.toyproject.repository.subproject.SubProjectRepository;
import com.dangsan.dotjoin.modules.toyproject.repository.subproject.kanbanboard.KanbanBoardRepository;
import com.dangsan.dotjoin.modules.toyproject.repository.subproject.kanbanboard.KanbanListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ToyProjectService {
    private final ToyProjectRepository toyProjectRepository;
    private final SubProjectRepository subProjectRepository;
    private final KanbanBoardRepository kanbanBoardRepository;
    private final KanbanListRepository kanbanListRepository;

    public void registerSubProject(Long projectId, RegisterSubProjectDto subProjectDto){
        ToyProject toyProject= toyProjectRepository.findById(projectId).get(); //.get()의 경우 결과값이 null일 경우 NoSuchElementException 발생
        SubProject subProject= new SubProject(toyProject, subProjectDto);
        subProjectRepository.save(subProject);


    }

}
