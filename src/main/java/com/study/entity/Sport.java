package com.study.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Sport {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "spo_no")
    private Long spoNo;

    @Column(name = "spo_name")
    private String spoName;




}
