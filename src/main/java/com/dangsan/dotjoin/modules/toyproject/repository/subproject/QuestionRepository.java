package com.dangsan.dotjoin.modules.toyproject.repository.subproject;

import com.dangsan.dotjoin.modules.toyproject.model.subproject.Question;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.SubProject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {


    public List<Question> findAllBySubProject(SubProject subProject);
}
