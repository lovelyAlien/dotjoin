package com.dangsan.dotjoin.modules.posting.controller;

import com.dangsan.dotjoin.modules.posting.service.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posting")
public class PostingController {

    private final PostingService postingService;

    @PostMapping("/write")
    private String writePosting() {
        return "/";
    }
}
