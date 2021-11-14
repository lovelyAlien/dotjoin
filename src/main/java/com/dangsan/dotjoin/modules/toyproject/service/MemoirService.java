package com.dangsan.dotjoin.modules.toyproject.service;

import com.dangsan.dotjoin.modules.toyproject.dto.memoir.InquireAllMemoirDto;
import com.dangsan.dotjoin.modules.toyproject.dto.memoir.InquireTargetMemoirDto;
import com.dangsan.dotjoin.modules.toyproject.dto.memoir.MemoirDto;
import com.dangsan.dotjoin.modules.toyproject.dto.memoir.UpdateTargetMemoirDto;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.Memoir;
import com.dangsan.dotjoin.modules.toyproject.model.subproject.SubProject;
import com.dangsan.dotjoin.modules.toyproject.repository.subproject.MemoirRepository;
import com.dangsan.dotjoin.modules.toyproject.repository.subproject.SubProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemoirService {
    private final SubProjectRepository subProjectRepository;
    private final MemoirRepository memoirRepository;
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


    public InquireAllMemoirDto inquireAllMemoir(Long subProjectId){

        SubProject subProject=subProjectRepository.findById(subProjectId).get();

        InquireAllMemoirDto inquireAllMemoirDto=new InquireAllMemoirDto();
        inquireAllMemoirDto.setSubProjectId(subProjectId);
        for(Memoir memoir: subProject.getProjectMemoirs()){
            MemoirDto memoirDto=new MemoirDto(memoir);
            inquireAllMemoirDto.getMemoirDtoList().add(memoirDto);
        }
        return inquireAllMemoirDto;

    }
    public void updateTargetMemoir(Long memoirId, UpdateTargetMemoirDto updateTargetMemoirDto){

        Memoir memoir=memoirRepository.findById(memoirId).get();

        memoir.update(updateTargetMemoirDto);

        memoirRepository.save(memoir);
    }

    public void deleteTargetMemoir(Long memoirId){
        memoirRepository.deleteById(memoirId);

    }
}
