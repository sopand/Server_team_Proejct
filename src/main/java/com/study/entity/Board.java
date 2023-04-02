package com.study.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Board{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardNo;

    @ManyToOne
    @JoinColumn(name="no")
    private User user;

    @ManyToOne
    @JoinColumn(name = "spo_no")
    private Sport sport;

    @OneToMany(mappedBy ="board",cascade = CascadeType.ALL)
    private List<Club> club=new ArrayList<>();

    private String boardName;
    private String boardContent;

    @Temporal(TemporalType.TIMESTAMP)
    private Date boardPromiseFrom;

    @Temporal(TemporalType.TIMESTAMP)
    private Date boardPromiseUntil;

    private String boardMapCordx;
    private String boardMapCordy;

    private String boardMapName;
    private String boardStatus;

    private int boardHit;
    private int boardPeople;
    private int boardNowPeople;

    @Temporal(TemporalType.DATE)
    private Date createDate;


    public void UpdateBoardHit(){
        this.boardHit=boardHit+1;
    }










}
