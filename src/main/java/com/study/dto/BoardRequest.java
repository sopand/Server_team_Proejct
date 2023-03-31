package com.study.dto;

import com.study.entity.Board;
import com.study.entity.Sport;
import lombok.Data;
import com.study.entity.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class BoardRequest {

    private Long spoNo;

    private String email;
    private String boardName;

    private String boardPromiseFrom;
    private String boardPromiseUntil;
    private String boardContent;

    private String imgList;
    private String boardMapCordx;
    private String boardMapCordy;

    private String boardMapName;

    private User user;
    private Sport sport;
    private int boardPeople;



    public Date changeDate(String time) throws ParseException {
        String newTime=time.replace("T"," ");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date formatDate = formatter.parse(newTime);
        return formatDate;
    }
    public Board toCreateBoard() throws ParseException {
        this.sport=Sport.builder().spoNo(spoNo).build();
        return Board.builder()
                .sport(sport)
                .user(user)
                .boardName(boardName)
                .boardPromiseFrom(changeDate(boardPromiseFrom))
                .boardPromiseUntil(changeDate(boardPromiseUntil))
                .boardContent(boardContent)
                .boardMapCordx(boardMapCordx)
                .boardMapCordy(boardMapCordy)
                .boardMapName(boardMapName)
                .boardPeople(boardPeople)
                .createDate(new Date())
                .build();
    }

}
