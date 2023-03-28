package com.study.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Sport {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "spo_no")
    private Long spoNo;

    @Column(name = "spo_name")
    private String spoName;


    @OneToMany(mappedBy = "sport",cascade = CascadeType.ALL)
    private List<User> user;



}
