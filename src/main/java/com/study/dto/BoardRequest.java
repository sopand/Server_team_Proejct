package com.study.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BoardRequest {

    private Long spoNo;
    private String boardName;
    private Date boardDay;
    private String boardPromiseFrom;
    private String boardPromiseUntil;

    private String boardMapCordx;
    private String boardMapCordy;
}
