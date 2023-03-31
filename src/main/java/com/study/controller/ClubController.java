package com.study.controller;

import com.study.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class ClubController {

    private final ClubService classService;

    @ResponseBody
    @PostMapping("/boards/club")
    public Long createClub(){

        return 1L;
    }

}
