package com.study.dto;

import com.study.entity.Review;
import lombok.Getter;

import java.util.Date;

@Getter
public class ReviewResponse {

    private Long reviewNo;
    private Long boardNo;
    private String reviewContent;
    private String reviewWriter;
    private Date reviewDate;
    private Long reviewParent;
    private int reviewDepth;
    private int reviewGroupSize;

    public ReviewResponse(Review entity){
        this.reviewNo=entity.getReviewNo();
        this.boardNo=entity.getBoard().getBoardNo();
        this.reviewContent=entity.getReviewContent();
        this.reviewWriter=entity.getReviewWriter();
        this.reviewDate=entity.getReviewDate();
        this.reviewParent=entity.getReviewParent();
        this.reviewDepth=entity.getReviewDepth();
        this.reviewGroupSize=entity.getReviewGroupSize();

    }
}
