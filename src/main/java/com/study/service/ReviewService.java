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

import static com.study.service.DuplicationService.setPagingData;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;

    /**
     * 리뷰를 생성하는 기능을 담당하는 로직
     * @param reviewRequest = 사용자가 입력한 리뷰의 데이터정보가 들어있는 객체
     * @return = 리뷰 생성의 성공여부를 판단하기위해 응답객체로 변환하여 리턴
     */
    public ReviewResponse createReview(ReviewRequest reviewRequest){
        return new ReviewResponse(reviewRepository.save(reviewRequest.createReviewEntity()));
    }


    /**
     * 게시글 상세페이지에서 게시글에 대한 리뷰를 출력시켜주기위한 로직
     * @param pageable = 페이징 데이터를 가지고 있는 객체
     * @param boardNo = 게시글의 고유번호
     * @return = 페이징 처리를 위한 메서드, View에 출력될 데이터를 가공하기 위한 용도
     */
    public PagingListGroup findReviewList(Pageable pageable,Long boardNo){
        Page<Review> getReviewPagingList=reviewRepository.findByBoardBoardNo(pageable,boardNo);
        List<ReviewResponse> pagingReviewResponse = getReviewPagingList.stream().filter(entity -> getReviewPagingList != null).map(ReviewResponse::new).toList();
        return setPagingData(getReviewPagingList,pagingReviewResponse);

    }


}
