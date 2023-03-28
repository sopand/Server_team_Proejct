package com.study.dto;

import com.study.entity.Sport;
import com.study.entity.User;
import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Data
public class UserRequest {
    private String nickname;
    private String name;
    private String email;
    private String password;
    private String tel;
    private Long sportNo;
    private String sportTimeFrom;

    private String sportTimeUntil;

    public User toCreateUserEntity() throws ParseException {
        Sport setSportId=Sport.builder().spoNo(sportNo).build();
        SimpleDateFormat formatter =new SimpleDateFormat("kk:mm:ss");
        return User.builder()
                .nickname(nickname)
                .name(name)
                .email(email)
                .password(password)
                .tel(tel)
                .sport(setSportId)
                .sportTimeFrom(formatter.parse(sportTimeFrom))
                .sportTimeUntil(formatter.parse(sportTimeUntil))
                .build();
    }
}

