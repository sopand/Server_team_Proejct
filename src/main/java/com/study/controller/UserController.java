package com.study.controller;

import com.study.dto.SportResponse;
import com.study.dto.UserRequest;
import com.study.dto.UserResponse;
import com.study.service.SportService;
import com.study.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
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

    /**
     * 회원의 아이디를 생성하기 위한 기능을 하는 맵핑,
     * @param userRequest  = 사용자가 회원 등록을 하기위해 입력한 여러 데이터들이 모여있는 Request객체,
     * @return = 회원가입 완료후 이동할 View페이지의 이름,
     * @throws Exception
     */
    @PostMapping("/users")
    public String createUser(UserRequest userRequest) throws Exception {
        userService.createUser(userRequest);
        return"redirect:/users/login";
    }

    @GetMapping("/users/detail")
    public String UserUpdateForm(Principal principal,Model model){
        String email=principal.getName();
        UserResponse getUser=userService.findUserByEmail(email);
        List<SportResponse> getSports=sportService.findSports();
        model.addAttribute("getSports",getSports);
        model.addAttribute("user",getUser);
        return "adduser";
    }


}
