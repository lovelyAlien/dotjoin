package com.dangsan.dotjoin.modules.toyproject.service;

import com.dangsan.dotjoin.modules.account.model.Account;
import com.dangsan.dotjoin.modules.account.repository.AccountRepository;
import com.dangsan.dotjoin.modules.toyproject.dto.toyproject.InquireAllToyProjectDto;
import com.dangsan.dotjoin.modules.toyproject.dto.toyproject.InquireTargetToyProjectDto;
import com.dangsan.dotjoin.modules.toyproject.dto.toyproject.RegisterToyProjectDto;
import com.dangsan.dotjoin.modules.toyproject.dto.toyproject.UpdateTargetToyProjectDto;
import com.dangsan.dotjoin.modules.toyproject.model.ToyProject;
import com.dangsan.dotjoin.modules.toyproject.repository.ToyProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ToyProjectService {

    private final ToyProjectRepository toyProjectRepository;
    private final AccountRepository accountRepository;

    public InquireTargetToyProjectDto inquireTargetToyProject(Long projectId){
        System.out.println("projectId: "+ projectId);
        System.out.println("현 위치: ToyProjectService");

        ToyProject toyProject= toyProjectRepository.findById(projectId).get(); //.get()의 경우 결과값이 null일 경우 NoSuchElementException 발생

        System.out.println("Get Toyproject object");
        System.out.println(toyProject.toString());
        InquireTargetToyProjectDto inquireTargetToyProjectDto= new InquireTargetToyProjectDto(toyProject);

        return inquireTargetToyProjectDto;

    }

    public List<InquireAllToyProjectDto> inquireAllToyProject(){

        List<ToyProject> toyProjects= toyProjectRepository.findAll();
        List<InquireAllToyProjectDto> inquireAllToyProjectDtoList= new ArrayList<>();

        for(ToyProject toyProject: toyProjects){
            InquireAllToyProjectDto inquireAllToyProjectDto=new InquireAllToyProjectDto(toyProject);
            inquireAllToyProjectDtoList.add(inquireAllToyProjectDto);

        }
        return inquireAllToyProjectDtoList;
    }

    public void registerToyProject(RegisterToyProjectDto registerToyProjectDto){
        ToyProject toyProject=new ToyProject(registerToyProjectDto);
        toyProjectRepository.save(toyProject);

    }
    @Transactional
    public void updateTargetToyProject(Long projectId, UpdateTargetToyProjectDto targetToyProjectDto){
        ToyProject toyProject=toyProjectRepository.findById(projectId).get();
        toyProject.update(targetToyProjectDto);
        toyProjectRepository.save(toyProject);

    }
    @Transactional
    public void deleteTargetToyProject(Long projectId){

        toyProjectRepository.deleteById(projectId);
    }



    public List<InquireAllToyProjectDto> inquireWorkingToyProjects() {

        System.out.println("Today date: "+ LocalDate.now());
        ToyProject t= toyProjectRepository.findById(1L).get();
        System.out.println("Project's start and end: "+ t.getProjectStart()+", "+t.getProjectEnd());

        List <ToyProject> toyProjects= toyProjectRepository.findAllByProjectStartLessThanEqualAndProjectEndGreaterThanEqual(LocalDate.now(), LocalDate.now());
        List<InquireAllToyProjectDto> inquireAllToyProjectDtoList = new ArrayList<>();

        for(ToyProject toyProject: toyProjects){
            InquireAllToyProjectDto inquireAllToyProjectDto=new InquireAllToyProjectDto(toyProject);
            inquireAllToyProjectDtoList.add(inquireAllToyProjectDto);

        }

        return inquireAllToyProjectDtoList;
    }

    public void requestJoinToyProject(User user, Long projectId) throws Exception {
        ToyProject toyProject=toyProjectRepository.findById(projectId).get();

        Account requester=accountRepository.findByEmail(user.getUsername());

        if(toyProject.getRequesters().contains(requester))
            throw new Exception("Already requested");
        else if(toyProject.getMembers().contains(requester))
            throw new Exception("Already a member");

        toyProject.getRequesters().add(requester);

    }

    @Transactional
    public void acceptJoinToyProject(User user, Long requesterId, Long toyProjectId) throws Exception {

        Account manager= accountRepository.findByEmail(user.getUsername());
        Account requester=accountRepository.findById(requesterId).get();
        String[] authorities=manager.getRoles().split(",");
        if(Arrays.asList(authorities).contains("ADMIN")){

            ToyProject toyProject=toyProjectRepository.findById(toyProjectId).get();
            toyProject.getRequesters().remove(requester);

            toyProjectRepository.flush();

            toyProject.getMembers().add(requester);
        }
        else
            throw new Exception("엑세스할 수 있는 권한이 없습니다.");

    }
}

