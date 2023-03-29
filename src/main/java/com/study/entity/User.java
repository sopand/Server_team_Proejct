package com.study.entity;

import com.study.dto.UserRequest;
import jakarta.persistence.*;
import lombok.*;

import java.text.ParseException;
import java.util.Date;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@AllArgsConstructor
public class User extends BaseTimeEntity{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    private String nickname;
    private String name;
    private String email;
    private String password;
    private int age;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spo_no")
    private Sport sport;

    @Temporal(TemporalType.TIME)
    @Column(name = "sport_time_from")
    private Date sportTimeFrom;

    @Temporal(TemporalType.TIME)
    @Column(name = "sport_time_until")
    private Date sportTimeUntil;


    @Enumerated(EnumType.STRING)
    private Role role;

    private String message;

    @Column(name="oauth_member_check")
    private String oauthMemberCheck;





    public void CommonUserUpdate(UserRequest request) throws Exception {
        this.nickname=request.getNickname();
        this.name=request.getName();
        this.age=request.getAge();
        this.sport=request.toSportEntityChange();
        if(request.getSportTimeFrom()!=null){
            this.sportTimeUntil=request.toDateChanger(request.getSportTimeUntil());
        }
        if(request.getSportTimeUntil()!=null){
            this.sportTimeFrom=request.toDateChanger(request.getSportTimeFrom());
        }
    }

    public void oAuthUserUpdate(UserRequest request) throws Exception{
        CommonUserUpdate(request);
        if(oauthMemberCheck.equals(oAuthChk.OAUTH_USER.getStatus())){
            this.oauthMemberCheck=oAuthChk.OAUTH_USER_PASSWORD_ON.getStatus();
        }
    }
    public void updateUser(UserRequest request) throws Exception {
        this.password=request.getPassword();
        CommonUserUpdate(request);
    }
}
