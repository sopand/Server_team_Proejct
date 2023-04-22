package com.study.dto;

import com.study.entity.User;
import lombok.Getter;

import java.util.Date;

@Getter

public class UserResponse {
    private Long no;
    private String name;
    private String nickname;
    private String email;
    private String password;
    private int age;
    private Long spoNo;
    private Date sportTimeFrom;
    private Date sportTimeUntil;

    private String role;
    private String oauthMemberCheck;

    public UserResponse(User entity){
        this.no=entity.getNo();
        this.name= entity.getName();
        this.nickname=entity.getNickname();
        this.email= entity.getEmail();
        this.password= entity.getPassword();
        this.age= entity.getAge();
        this.spoNo=entity.getSport().getSpoNo();
        this.sportTimeFrom=entity.getSportTimeFrom();
        this.sportTimeUntil=entity.getSportTimeUntil();
        this.role=entity.getRole().name();
        this.oauthMemberCheck=entity.getOauthMemberCheck();
    }

}
