package com.dangsan.dotjoin.modules.toyproject.service;

import com.dangsan.dotjoin.modules.toyproject.dto.subproject.RegisterSubProject;
import com.dangsan.dotjoin.modules.toyproject.model.ToyProject;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.KanbanBoard;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.SubProject;
import com.dangsan.dotjoin.modules.toyproject.repository.ToyProjectRepository;
import com.dangsan.dotjoin.modules.toyproject.repository.subproject.SubProjectRepository;
import com.dangsan.dotjoin.modules.toyproject.repository.subproject.kanbanboard.KanbanBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ToyProjectService {
    private final ToyProjectRepository toyProjectRepository;
    private final SubProjectRepository subProjectRepository;
    private final KanbanBoardRepository kanbanBoardRepository;


    public void registerSubProject(Long projectId, RegisterSubProject subProjectDto){
        ToyProject toyProject= toyProjectRepository.findById(projectId).get(); //.get()의 경우 결과값이 null일 경우 NoSuchElementException 발생
        SubProject subProject=subProjectRepository.save(new SubProject(toyProject, subProjectDto));





        //subproject 생성 시, kanbanboard는 자동으로 생성
        KanbanBoard kanbanBoard=new KanbanBoard(toyProject, subProject);
        kanbanBoardRepository.save(kanbanBoard);

    }

}