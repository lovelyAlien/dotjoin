package com.dangsan.dotjoin.modules.toyproject.service;

import com.dangsan.dotjoin.modules.account.model.Account;
import com.dangsan.dotjoin.modules.toyproject.model.ToyProject;
import com.dangsan.dotjoin.modules.toyproject.repository.ToyProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
@ExtendWith(MockitoExtension.class)
@Transactional
@SpringBootTest
class ToyProjectServiceTest {
    private  Account requester;
    private  Account manager;
    private  ToyProject toyProject;

    @Autowired
    private ToyProjectRepository toyProjectRepository;

    @BeforeEach
    public  void setUp() {
        requester=new Account();
        requester.setId(1L);

        manager=new Account();
        manager.setId(2L);

        toyProject=new ToyProject();

    }

    @Test
    void inquireTargetToyProject() {
    }

    @Test
    void inquireAllToyProject() {
    }

    @Test
    void registerToyProject() {
    }

    @Test
    void updateTargetToyProject() {
    }

    @Test
    void deleteTargetToyProject() {
    }

    @Test
    void inquireWorkingToyProjects() {
    }

    @Test
    void requestJoinToyProject() {

        requester.setEmail("asdf1234@gmail.com");

        toyProjectRepository.save(toyProject);
        toyProject.getRequesters().add(requester);
        assertEquals(1,toyProject.getRequesters().size());


    }

    @Test
    void acceptJoinToyProject() {
        requester.setEmail("asdf1234@gmail.com");

        toyProjectRepository.save(toyProject);
        toyProject.getRequesters().add(requester);

        if(toyProject.getRequesters().contains(requester)){
            toyProject.getRequesters().remove(requester);
            toyProject.getMembers().add(requester);


            assertTrue(toyProject.getMembers().contains(requester));
            assertEquals(1, toyProject.getMembers().size());
            assertTrue(toyProject.getRequesters().isEmpty());

        }


    }
}