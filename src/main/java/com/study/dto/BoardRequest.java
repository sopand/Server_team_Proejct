package com.study.dto;

import com.study.entity.Board;
import com.study.entity.Sport;
import lombok.Data;
import com.study.entity.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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


    public Date changeDate(String day,String time) throws ParseException {
        String dayTime=day+" "+time+".0";
        System.out.println(dayTime);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        return formatter.parse(dayTime);
    }
    public Board toCreateBoard() throws ParseException {
        Sport sport=Sport.builder().spoNo(spoNo).build();
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
                .build();
    }

}
