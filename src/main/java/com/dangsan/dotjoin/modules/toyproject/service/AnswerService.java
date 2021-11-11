package com.dangsan.dotjoin.modules.toyproject.service;


import com.dangsan.dotjoin.modules.account.model.Account;
import com.dangsan.dotjoin.modules.account.repository.AccountRepository;
import com.dangsan.dotjoin.modules.toyproject.dto.answer.InquireTargetAnswerDto;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.Answer;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.Question;
import com.dangsan.dotjoin.modules.toyproject.repository.subproject.AnswerRepository;
import com.dangsan.dotjoin.modules.toyproject.repository.subproject.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final AccountRepository accountRepository;



    public InquireTargetAnswerDto inquireTargetAnswer(Long questionId, Long answerId){

        Answer answer= answerRepository.findById(answerId).get();

        InquireTargetAnswerDto inquireTargetAnswerDto= new InquireTargetAnswerDto(questionId, answer);

        return inquireTargetAnswerDto;

    }


    public void registerAnswer(Long questionId, User user){
        Question question= questionRepository.findById(questionId).get();
        Account answerer= accountRepository.findByEmail(user.getUsername());

        Answer answer= new Answer(question, answerer);

        answerRepository.save(answer);
    }

    @Transactional
    public void updateTargetAnswer(Long answerId, String detail){

        Answer answer= answerRepository.findById(answerId).get();

        answer.setDetail(detail);
        answerRepository.save(answer);

    }

    @Transactional
    public void deleteTargetAnswer(Long answerId){

        answerRepository.deleteById(answerId);
    }


}
