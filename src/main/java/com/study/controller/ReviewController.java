package com.study.controller;


import com.study.dto.ReviewRequest;
import com.study.dto.ReviewResponse;
import com.study.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;


    @PostMapping("/boards/review")
    public ReviewResponse createReview(ReviewRequest reviewRequest){

        return reviewService.createReview(reviewRequest);

    }

}
