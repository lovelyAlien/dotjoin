package com.dangsan.dotjoin.modules.toyproject.service;


import com.dangsan.dotjoin.modules.toyproject.dto.answer.AnswerDto;
import com.dangsan.dotjoin.modules.toyproject.dto.answer.AnswerLikeDto;
import com.dangsan.dotjoin.modules.toyproject.dto.question.QuestionDto;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.Answer;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.AnswerLike;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.Question;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.SubProject;
import com.dangsan.dotjoin.modules.toyproject.repository.subproject.AnswerLikeRepository;
import com.dangsan.dotjoin.modules.toyproject.repository.subproject.AnswerRepository;
import com.dangsan.dotjoin.modules.toyproject.repository.subproject.QuestionRepository;
import com.dangsan.dotjoin.modules.toyproject.repository.subproject.SubProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final AnswerLikeRepository answerLikeRepository;
    private final SubProjectRepository subProjectRepository;

    public List<QuestionDto> inquireQuestionDto(Long subProjectId){
        List<QuestionDto> questionDtoList=new ArrayList<>();

        SubProject subProject= subProjectRepository.findById(subProjectId).get();

        List<Question> questions=  questionRepository.findAllBySubProject(subProject);

        for(Question question: questions){
            QuestionDto questionDto=new QuestionDto(question);

            List<AnswerDto> answerDtoList=new ArrayList<>();
            List<Answer> answers= answerRepository.findAllByQuestion(question);
            for(Answer answer: answers){
                AnswerDto answerDto=new AnswerDto(answer);
                double totalScore=0;
                List<AnswerLikeDto> answerLikeDtoList= new ArrayList<>();
                List<AnswerLike> answerLikes=answerLikeRepository.findAllByAnswer(answer);

                for(AnswerLike answerLike: answerLikes){
                    AnswerLikeDto answerLikeDto= new AnswerLikeDto(answerLike);
                    totalScore+=answerLikeDto.getRate();
                    answerLikeDtoList.add(answerLikeDto);
                }
                answerDto.setAnswerLikeDtoList(answerLikeDtoList, totalScore);
                answerDtoList.add(answerDto);
            }
            questionDto.setAnswerDtoList(answerDtoList);
            questionDtoList.add(questionDto);
        }
        return questionDtoList;

    }







}
