package com.ssafy.ssafit.dao;

import com.ssafy.ssafit.domain.Review;

import java.util.List;

public interface ReviewMapper {
    // 리뷰 생성
    int insertReview(Review review);

    // 특정 영상에 대한 리뷰 조회
    List<Review> selectReviewsByVideoId(String videoId);

    // 특정 리뷰 조회
    Review selectReviewById(String reviewId);

    // 리뷰 수정
    void updateReview(Review review);

    // 리뷰 삭제
    void deleteReview(String reviewId);
}
