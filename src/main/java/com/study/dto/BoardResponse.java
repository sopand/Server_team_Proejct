package com.study.dto;

import com.study.entity.Board;
import com.study.entity.Img;
import com.study.entity.Sport;
import com.study.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class BoardResponse {

    private User user;
    private Sport sport;
    private Long boardNo;

    private String boardName;
    private String boardContent;
    private Date boardPromiseFrom;
    private Date boardPromiseUntil;
    private String boardMapCordx;
    private String boardMapCordy;
    private String boardMapName;
    private String boardStatus;
    private int boardHit;

    private List<ClubResponse> club;

    private Date createDate;

    private int boardPeople;
    private int boardNowPeople;

    public BoardResponse(Board entity){
        this.user=entity.getUser();
        this.sport=entity.getSport();
        this.boardNo=entity.getBoardNo();
        this.boardName=entity.getBoardName();
        this.boardContent=entity.getBoardContent();
        this.boardPromiseFrom=entity.getBoardPromiseFrom();
        this.boardPromiseUntil=entity.getBoardPromiseUntil();
        this.boardMapCordx=entity.getBoardMapCordx();
        this.boardMapCordy=entity.getBoardMapCordy();
        this.boardMapName=entity.getBoardMapName();
        this.boardStatus=entity.getBoardStatus();
        this.createDate=entity.getCreateDate();
        if(entity.getClub()!=null){
            this.club=entity.getClub().stream().map(ClubResponse::new).toList();
            System.out.println("리스트"+club);
            System.out.println("리스트 길이"+club.size());
        }
        this.boardHit=entity.getBoardHit();
        this.boardPeople=entity.getBoardPeople();
        this.boardNowPeople=entity.getBoardNowPeople();
    }



}
