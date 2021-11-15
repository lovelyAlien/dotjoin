package com.dangsan.dotjoin.modules.toyproject.service;

import com.dangsan.dotjoin.modules.toyproject.dto.memoir.*;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.Memoir;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.SubProject;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.Url;
import com.dangsan.dotjoin.modules.toyproject.repository.subproject.MemoirRepository;
import com.dangsan.dotjoin.modules.toyproject.repository.subproject.SubProjectRepository;
import com.dangsan.dotjoin.modules.toyproject.repository.subproject.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoirService {
    private final SubProjectRepository subProjectRepository;
    private final MemoirRepository memoirRepository;
    private final UrlRepository urlRepository;



    public Long registerMemoir(Long subProjectId, String title){
        SubProject subProject= subProjectRepository.findById(subProjectId).get();


        Memoir memoir= memoirRepository.save(new Memoir(subProject, title));

        return memoir.getId();

    }

    public InquireTargetMemoirDto inquireTargetMemoir(Long memoirId){

        Memoir memoir= memoirRepository.findById(memoirId).get();

        InquireTargetMemoirDto inquireTargetMemoirDto=new InquireTargetMemoirDto(memoir);

        return inquireTargetMemoirDto;
    }


    public List<InquireAllMemoirDto> inquireAllMemoirInSubProject(Long subProjectId){

        SubProject subProject=subProjectRepository.findById(subProjectId).get();

        List<InquireAllMemoirDto> inquireAllMemoirDtoList=new ArrayList<>();

        for(Memoir memoir: subProject.getProjectMemoirs()){

            InquireAllMemoirDto inquireAllMemoirDto=new InquireAllMemoirDto(memoir);
            inquireAllMemoirDtoList.add(inquireAllMemoirDto);
        }
        return inquireAllMemoirDtoList;

    }
    public void updateTargetMemoir(Long memoirId, UpdateTargetMemoirDto updateTargetMemoirDto){

        Memoir memoir=memoirRepository.findById(memoirId).get();

        memoir.update(updateTargetMemoirDto);

        memoirRepository.save(memoir);
    }

    public void deleteTargetMemoir(Long memoirId){
        memoirRepository.deleteById(memoirId);

    }

    public List<InquireAllMemoirDto> inquireAllMemoir() {
        List<Memoir> memoirs = memoirRepository.findAll();

        List<InquireAllMemoirDto> inquireAllMemoirDtoList=new ArrayList<>();

        for(Memoir memoir: memoirs){

            InquireAllMemoirDto inquireAllMemoirDto=new InquireAllMemoirDto(memoir);
            inquireAllMemoirDtoList.add(inquireAllMemoirDto);
        }
        return inquireAllMemoirDtoList;
    }


    public Long registerUrl(Long memoirId, RegisterUrlDto registerUrlDto){

        Memoir memoir=memoirRepository.findById(memoirId).get();
        Url url=urlRepository.save(new Url(memoir, registerUrlDto));

        return url.getId();
    }


    public void updateAllUrl(Long memoirId, List<UpdateTargetUrlDto> updateTargetUrlDtoList){



        for(UpdateTargetUrlDto updateTargetUrlDto: updateTargetUrlDtoList){
            Url url=urlRepository.findById(updateTargetUrlDto.getUrlId()).get();
            url.update(updateTargetUrlDto);
            urlRepository.save(url);
        }
    }
}
