package com.dangsan.dotjoin.modules.posting.service;

import com.dangsan.dotjoin.modules.posting.form.ResponsePostingsForm;
import com.dangsan.dotjoin.modules.posting.form.WritePostingForm;
import com.dangsan.dotjoin.modules.posting.model.Posting;
import com.dangsan.dotjoin.modules.posting.repository.PostingRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostingService {

    private final PostingRepository postingRepository;
    private final ModelMapper modelMapper;

    // 전체 데이터 검색
    public ResponsePostingsForm getPostings() {
        ResponsePostingsForm postingsForm = new ResponsePostingsForm();
        List<Posting> postings = postingRepository.findAll();

        postingsForm.setPostingList(postings);

        return postingsForm;
    }

    // 새로운 posting 등록
    public void writePosting(String contents) {
        WritePostingForm postingForm = new WritePostingForm();
        postingForm.setContents(contents);

        Posting posting = modelMapper.map(postingForm, Posting.class);

        postingRepository.save(posting);
    }
}
