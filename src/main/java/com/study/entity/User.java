package com.study.entity;

import jakarta.persistence.*;
import lombok.*;

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



}
