package com.study.controller;

import com.study.dto.SportResponse;
import com.study.service.SportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class FormController {

    private final SportService sportService;

    @GetMapping("/index")
    public String indexFormM(){

        return "index";
    }

    @ResponseBody
    @GetMapping("/sports/category")
    public List<SportResponse> findBySportCategory(){

        return sportService.findSports();
    }

}
