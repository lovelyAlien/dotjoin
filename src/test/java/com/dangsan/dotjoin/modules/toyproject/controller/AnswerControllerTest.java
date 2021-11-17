package com.dangsan.dotjoin.modules.toyproject.controller;

import com.dangsan.dotjoin.modules.account.model.Account;
import com.dangsan.dotjoin.modules.account.repository.AccountRepository;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.Answer;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.Question;
import com.dangsan.dotjoin.modules.toyproject.repository.subproject.AnswerRepository;
import com.dangsan.dotjoin.modules.toyproject.repository.subproject.QuestionRepository;
import com.dangsan.dotjoin.modules.toyproject.service.AnswerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import javax.transaction.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
@Transactional
@SpringBootTest
class AnswerControllerTest {

    @MockBean
    private QuestionRepository questionRepository;

    @MockBean
    private AccountRepository accountRepository;

    @SpyBean
    private AnswerRepository answerRepository;

    @Autowired
    private AnswerService answerService;


    @Before
    void setUp(){

    }


    @Test
    void inquireTargetAnswerTest() {


    }

    @Test
    void inquireAllAnswerTest() {
    }




    @Test
    void registerAnswerTest() {


        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        User user= new User("qwer1234@gmail.com", "qwer1234", authorities);

        Question question =new Question();
        question.setId(1L);

        Account answerer=new Account();
        answerer.setId(1L);
        answerer.setEmail("qwer1234@gmail.com");

        given(accountRepository.findByEmail(any())).willReturn(answerer);
        given(questionRepository.findById(any())).willReturn(Optional.of(question));


        //when
        Long id= answerService.registerAnswer(1L,user);

        //then
        Optional<Answer> answer=answerRepository.findById(id);
        assertTrue(answer.isPresent());
        assertEquals(answer.get().getAnswerer(), answerer);
        assertEquals(answer.get().getQuestion(), question);

    }

    @Test
    void updateTargetAnswerTest() {
        Account answerer= new Account();
        answerer.setId(1L);
        answerer.setEmail("asdf1234@gmail.com");
        Question question=new Question();
        question.setId(1L);
        Answer answer=new Answer(question, answerer);
        answerRepository.save(answer);
        Long answerId=answer.getId();

//        given(answerRepository.findById(any())).willReturn(Optional.of(answer));

//        //when
//        answerService.updateTargetAnswer(answerId, "It is answer test");


//        //then
//        assertEquals("It is answer test", answer.getDetail());
//        then(answerRepository).should(times(2)).save(answer);
//        then(answerRepository).shouldHaveNoInteractions();

    }

    @Test
    void deleteTargetAnswerTest() {
    }
}