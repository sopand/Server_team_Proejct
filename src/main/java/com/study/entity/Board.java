package com.study.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_no")
    private Long boardNo;



    @ManyToOne
    @JoinColumn(name="no")
    private User user;

    @ManyToOne
    @JoinColumn(name = "spo_no")
    private Sport sport;









}
