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



    public void updateUser(UserRequest request) throws Exception {

        this.nickname=request.getNickname();
        this.name=request.getName();
        this.password=request.getPassword();
        this.age=request.getAge();
        this.sport=request.toSportEntityChange();
        this.sportTimeUntil=request.toDateChanger(request.getSportTimeUntil());
        this.sportTimeFrom=request.toDateChanger(request.getSportTimeFrom());
    }

}
