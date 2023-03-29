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
import org.springframework.web.bind.annotation.PutMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final SportService sportService;
    private final UserService userService;


    /**
     * 유저의 정보와 관련된 데이터에서 자주 사용하는 principal을 인자로받아 로그인정보에서 로그인된 사람의 아이디값을 리턴시켜준다.
     * @param principal = 로그인 정보를 가지고있는 객체
     * @return = 로그인 정보에서 ID값을 추출,
     */
    public String getEmail(Principal principal){
        return principal.getName();
    }

    /**
     * 사용자의 로그인을 위한 View 페이지로 이동시켜주는 맵핑,
     * @return = 로그인을 담당하는 html 페이지인 login.html로 이동,
     */
    @GetMapping("/users/login")
    public String LoginForm(){
        return "login";
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

    /**
     * 회원의 정보 수정페이지로 이동시켜주는 맵핑
     * @param model = View에 뿌려줄 데이터를 설정해주는 객체,
     * @param principal = 현재 로그인한 사람의 데이터를 가져오기위한 객체,
     * @return
     */
    @GetMapping("/users/detail")
    public String UserUpdateForm(Model model,Principal principal){
        UserResponse getUser=userService.findUserByEmail(getEmail(principal)); // 로그인한 사용자의 현재 데이터를 가져옴
        List<SportResponse> getSports=sportService.findSports(); // View의 select에 출력해줄 데이터를 받아오는용도.
        model.addAttribute("getSports",getSports);
        model.addAttribute("user",getUser);
        return "adduser";
    }


    @PutMapping("/users")
    public String modifyUser(UserRequest userRequest){
        userService.modifyUser(userRequest);
        return "redirect:/index";
    }





}
