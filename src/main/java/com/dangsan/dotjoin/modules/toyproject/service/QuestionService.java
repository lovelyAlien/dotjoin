package com.dangsan.dotjoin.modules.toyproject.service;

import com.dangsan.dotjoin.modules.account.model.Account;
import com.dangsan.dotjoin.modules.account.repository.AccountRepository;
import com.dangsan.dotjoin.modules.toyproject.dto.question.InquireTargetQuestionDto;
import com.dangsan.dotjoin.modules.toyproject.dto.question.UpdateTargetQuestionDto;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.Answer;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.Question;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.SubProject;
import com.dangsan.dotjoin.modules.toyproject.repository.subproject.QuestionRepository;
import com.dangsan.dotjoin.modules.toyproject.repository.subproject.SubProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final SubProjectRepository subProjectRepository;
    private final AccountRepository accountRepository;
    public InquireTargetQuestionDto inquireTargetQuestion(Long subProjectId, Long questionId){

        Question question=questionRepository.findById(questionId).get();

        InquireTargetQuestionDto inquireTargetQuestionDto= new InquireTargetQuestionDto(subProjectId, question);

        return inquireTargetQuestionDto;


    }

    public Long registerQuestion(Long subProjectId, User user){
        SubProject subProject=subProjectRepository.findById(subProjectId).get();
        Account questioner=accountRepository.findByEmail(user.getUsername());

        Question question= questionRepository.save(new Question(subProject, questioner));

        return question.getId();

    }

    @Transactional
    public void updateTargetQuestion(Long questionId, UpdateTargetQuestionDto updateTargetQuestionDto){

        Question question= questionRepository.findById(questionId).get();

        question.update(updateTargetQuestionDto);
        questionRepository.save(question);

    }

    @Transactional
    public void deleteTargetQuestion(Long questionId){

        questionRepository.deleteById(questionId);
    }



}
