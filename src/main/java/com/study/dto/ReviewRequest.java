package com.study.dto;

import com.study.entity.Review;
import lombok.Data;

@Data
public class ReviewRequest {

    private Long boardNo;
    private String reviewContent;
    private String reviewWriter;
    private Long reviewParent;


    public Review createReviewEntity(){
       return Review.builder().boardNo(boardNo).reviewContent(reviewContent).reviewWriter(reviewWriter).build();
    }

    public Review createReviewChildEntity(){
        return Review.builder().boardNo(boardNo).reviewContent(reviewContent).reviewWriter(reviewWriter).reviewParent(reviewParent).build();
    }

}
