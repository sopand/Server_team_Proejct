package com.study.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Review {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long reviewNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_no")
    private Board board;


    private String reviewContent;

    private String reviewWriter;

    @Temporal(TemporalType.TIMESTAMP)
    private Date reviewDate;

    private Long reviewParent;

    private int reviewDepth;

    private int reviewGroupSize;


    @Builder
    public Review(Long boardNo,String reviewContent,String reviewWriter,Long reviewParent,int reviewDepth,int reviewGroupSize){
        if(boardNo!=null){
            this.board=Board.builder().boardNo(boardNo).build();
        }
        this.reviewContent=reviewContent;
        this.reviewWriter=reviewWriter;
        this.reviewParent=reviewParent;
        this.reviewDepth=reviewDepth;
        this.reviewGroupSize=reviewGroupSize;
        this.reviewDate=new Date();

    }





}
