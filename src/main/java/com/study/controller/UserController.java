package com.study.controller;

import com.study.dto.SportResponse;
import com.study.dto.UserRequest;
import com.study.service.SportService;
import com.study.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final SportService sportService;
    private final UserService userService;

    /**
     * 사용자의 로그인을 위한 View 페이지로 이동시켜주는 맵핑,
     * @return = 로그인을 담당하는 html 페이지인 login.html로 이동,
     */
    @GetMapping("/users/login")
    public String LoginForm(){
        return "login";
    }

    @PostMapping("/users/login")
    public String Login(UserRequest userRequest){
        return "index";
    }

    /**
     * 사용자의 로그인을 위한 아이디를 생성하는 페이지로 이동시켜주는 맵핑,
     * @param model = 아이디를 생성할때 설정해야하는 값중 하나인 운동에 대한 select 값을 View로 보내주기 위한 Model 객체,
     * @return = 아이디 생성을 위한 페이지 adduser.html로 이동
     */
    @GetMapping("/users")
    public String AddUserForm(Model model){
        List<SportResponse> getSports=sportService.findSports();
        model.addAttribute("getSports",getSports);
        return "adduser";
    }
    @PostMapping("/users")
    public String createUser(UserRequest userRequest) throws Exception {
        userService.createUser(userRequest);
        return"redirect:/users/login";
    }


}
