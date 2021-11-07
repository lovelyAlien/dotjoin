package com.dangsan.dotjoin.modules.toyproject.repository;

import com.dangsan.dotjoin.modules.toyproject.dto.toyproject.ProjectInWork;
import com.dangsan.dotjoin.modules.toyproject.model.ToyProject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ToyProjectRepository extends JpaRepository<ToyProject, Long> {

    List<ProjectInWork> findAllByProjectStartGreaterThanEqualAndProjectEndLessThanEqual(LocalDate today);
    List<ProjectInWork> findByProjectStartAfterAndProjectEndBefore(LocalDate today1, LocalDate today2);
}
