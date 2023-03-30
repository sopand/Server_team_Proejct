package com.study.controller;

import com.study.dto.SportResponse;
import com.study.service.SportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final SportService sportService;
    @GetMapping("/boards/list")
    public String findBoardList(Model model){
        return "boardlist";
    }

    @GetMapping("/boards")
    public String BoardAddForm(Model model){
        List<SportResponse> getSports=sportService.findSports();
        model.addAttribute("getSports",getSports);

        return "boardadd";
    }
}
