package com.study.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clubNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_no")
    private Board board;

    private String clubEmail;






}
