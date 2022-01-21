package com.dangsan.dotjoin.modules.toyproject.service;


import com.dangsan.dotjoin.modules.account.model.Account;
import com.dangsan.dotjoin.modules.account.model.UserAccount;
import com.dangsan.dotjoin.modules.account.repository.AccountRepository;
import com.dangsan.dotjoin.modules.toyproject.dto.answer.InquireAllAnswerDto;
import com.dangsan.dotjoin.modules.toyproject.dto.answer.InquireTargetAnswerDto;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.Answer;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.Question;
import com.dangsan.dotjoin.modules.toyproject.repository.subproject.AnswerRepository;
import com.dangsan.dotjoin.modules.toyproject.repository.subproject.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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

    public List<InquireAllAnswerDto> inquireAllAnswerInQuestion(Long questionId){
        Question question= questionRepository.findById(questionId).get();
        List<InquireAllAnswerDto> inquireAllAnswerDtoList=new ArrayList<>();
        for(Answer answer: question.getAnswers()){
            InquireAllAnswerDto inquireAllAnswerDto=new InquireAllAnswerDto(answer);
            inquireAllAnswerDtoList.add(inquireAllAnswerDto);
        }

        return inquireAllAnswerDtoList;
    }


    public Long registerAnswer(Long questionId, User user){
        Question question= questionRepository.findById(questionId).get();
        Account answerer= accountRepository.findByEmail(user.getUsername()).get();

        Answer answer= answerRepository.save(new Answer(question, answerer));

        return answer.getId();


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
