package com.dangsan.dotjoin.modules.account.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OAuthController {

        @GetMapping("/test")
        public String index() {
            return "Hello World";
        }


}
