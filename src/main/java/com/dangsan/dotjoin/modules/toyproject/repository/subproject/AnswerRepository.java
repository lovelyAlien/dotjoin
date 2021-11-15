package com.dangsan.dotjoin.modules.toyproject.repository.subproject;

import com.dangsan.dotjoin.modules.toyproject.model.subproject.Answer;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {


    List<Answer> findAllByQuestion(Question question);
}
