package com.study.service;


import com.study.dto.BoardResponse;
import com.study.dto.PagingListGroup;
import com.study.dto.ReviewRequest;
import com.study.dto.ReviewResponse;
import com.study.entity.Review;
import com.study.entity.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewResponse createReview(ReviewRequest reviewRequest){
        return new ReviewResponse(reviewRepository.save(reviewRequest.createReviewEntity()));
    }


    public PagingListGroup findReviewList(Pageable pageable){
        Page<Review> getReviewPagingList=reviewRepository.findAll(pageable);
        List<ReviewResponse> pagingReviewResponse = getReviewPagingList.stream().filter(entity -> getReviewPagingList != null).map(ReviewResponse::new).toList();
        return DuplicationService.setPagingData(getReviewPagingList,pagingReviewResponse);

    }


}
