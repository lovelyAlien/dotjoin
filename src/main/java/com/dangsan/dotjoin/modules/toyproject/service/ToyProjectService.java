package com.dangsan.dotjoin.modules.toyproject.service;

import com.dangsan.dotjoin.modules.toyproject.dto.toyproject.ProjectInWork;
import com.dangsan.dotjoin.modules.toyproject.repository.ToyProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ToyProjectService {

//    private final ToyProjectRepository projectRepository;
//
//    public List<ProjectInWork> findMyProject() {
//        List<ProjectInWork> projects = projectRepository.findAllByProjectStartGreaterThanEqualAndProjectEndLessThanEqual(LocalDate.now());
//
//        return projects;
//    }
}
