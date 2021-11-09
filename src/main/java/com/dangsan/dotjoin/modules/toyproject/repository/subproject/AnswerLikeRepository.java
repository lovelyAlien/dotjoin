package com.dangsan.dotjoin.modules.toyproject.repository.subproject;

import com.dangsan.dotjoin.modules.toyproject.model.subproject.Answer;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.AnswerLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerLikeRepository extends JpaRepository<AnswerLike, Long> {


    List<AnswerLike> findAllByAnswer(Answer answer);
}
