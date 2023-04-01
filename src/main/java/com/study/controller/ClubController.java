package com.study.controller;

import com.study.dto.ClubRequest;
import com.study.dto.ClubResponse;
import com.study.oAuth.UserAdapter;
import com.study.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class ClubController {

    private final ClubService clubService;

    public static Long getUserNo(UserAdapter userAdapter){
        if(userAdapter!=null){
            return userAdapter.getUser().getNo();
        }else{
            return null;
        }
    }
    @ResponseBody
    @PostMapping("/boards/club")
    public ClubResponse createClub(@AuthenticationPrincipal UserAdapter userAdapter, ClubRequest clubRequest){
        clubRequest.setNo(getUserNo(userAdapter));
        return clubService.createClubUser(clubRequest);
    }

}
