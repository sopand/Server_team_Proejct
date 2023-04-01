package com.study.dto;

import com.study.oAuth.Role;
import com.study.entity.Sport;
import com.study.entity.User;
import com.study.oAuth.oAuthChk;
import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class UserRequest {

    private String nickname;


    private String name;

    private String email;

    private int age;
    private String beforePassword;

    private String password;
    private String passwordCheck;

    private Long sportNo;

    private String sportTimeFrom;


    private String sportTimeUntil;

    public Sport toSportEntityChange() {
        return Sport.builder().spoNo(sportNo).build();
    }

    public Date toDateChanger(String beforeDate) throws ParseException {
        if(beforeDate!=null){
            SimpleDateFormat formatter = new SimpleDateFormat("kk:mm:ss");
            return formatter.parse(beforeDate);
        }
        return null;

    }

    public User toCreateUserEntity() throws ParseException {
        return User.builder()
                .nickname(nickname)
                .name(name)
                .email(email)
                .password(password)
                .sport(toSportEntityChange())
                .sportTimeFrom(toDateChanger(sportTimeFrom))
                .sportTimeUntil(toDateChanger(sportTimeUntil))
                .role(Role.ROLE_USER)
                .oauthMemberCheck(oAuthChk.NO_OAUTH_USER.getStatus())
                .build();
    }


}

