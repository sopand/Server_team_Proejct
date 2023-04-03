package com.study.service;


import com.study.dto.ReviewRequest;
import com.study.dto.ReviewResponse;
import com.study.entity.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewResponse createReview(ReviewRequest reviewRequest){
        return new ReviewResponse(reviewRepository.save(reviewRequest.createReviewEntity()));
    }



}
