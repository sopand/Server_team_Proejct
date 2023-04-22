package com.study.controller;


import com.study.dto.PagingListGroup;
import com.study.dto.ReviewRequest;
import com.study.dto.ReviewResponse;
import com.study.entity.Board;
import com.study.service.DuplicationService;
import com.study.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final DuplicationService duplicationService;


    @PostMapping("/boards/review")
    public ReviewResponse createReview(ReviewRequest reviewRequest){

        return reviewService.createReview(reviewRequest);

    }

    @GetMapping("/boards/review")
    public PagingListGroup findReviewList(Long boardNo,@PageableDefault(page = 0, size = 10, sort = "reviewNo", direction = Sort.Direction.DESC) Pageable pageable){
        return reviewService.findReviewList(pageable,boardNo);

    }


}
