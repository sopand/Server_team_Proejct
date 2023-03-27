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
public class UserController {

    private final SportService sportService;

    @GetMapping("/users/login")
    public String LoginForm(){

        return "login";
    }

    @GetMapping("/users")
    public String AddUserForm(Model model){
        List<SportResponse> getSports=sportService.findSports();
        model.addAttribute("getSports",getSports);
        return "adduser";
    }
}
