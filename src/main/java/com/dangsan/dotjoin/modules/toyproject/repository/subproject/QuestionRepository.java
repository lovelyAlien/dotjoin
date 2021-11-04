package com.dangsan.dotjoin.modules.toyproject.repository.subproject;

import com.dangsan.dotjoin.modules.toyproject.model.subproject.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
