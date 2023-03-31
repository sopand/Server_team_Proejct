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
    private String boardDay;
    private String boardPromiseFrom;
    private String boardPromiseUntil;
    private String boardContent;

    private String imgList;
    private String boardMapCordx;
    private String boardMapCordy;

    private String boardMapName;

    private User user;
    private Sport sport;



    public Date changeDate(String day,String time) throws ParseException {
        String dayTime=day+" "+time;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.parse(dayTime);
    }
    public Board toCreateBoard() throws ParseException {
        this.sport=Sport.builder().spoNo(spoNo).build();
        return Board.builder()
                .sport(sport)
                .user(user)
                .boardName(boardName)
                .boardPromiseFrom(changeDate(boardDay,boardPromiseFrom))
                .boardPromiseUntil(changeDate(boardDay,boardPromiseUntil))
                .boardContent(boardContent)
                .boardMapCordx(boardMapCordx)
                .boardMapCordy(boardMapCordy)
                .boardMapName(boardMapName)
                .createDate(new Date())
                .build();
    }

}
