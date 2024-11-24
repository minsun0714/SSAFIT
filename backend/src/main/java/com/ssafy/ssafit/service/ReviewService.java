package com.ssafy.ssafit.service;

import com.ssafy.ssafit.dao.ReviewMapper;
import com.ssafy.ssafit.domain.Review;
import com.ssafy.ssafit.dto.request.ReviewRequestDTO;
import com.ssafy.ssafit.dto.response.ReviewResponseVO;
import com.ssafy.ssafit.exception.MemberNotAuthenticatedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ReviewService {

    private final ReviewMapper reviewMapper;

    public ReviewService(ReviewMapper reviewMapper) {
        this.reviewMapper = reviewMapper;
    }

    // 리뷰 생성
    @Transactional
    public ReviewResponseVO createReview(ReviewRequestDTO reviewRequestDTO) {
        // 사용자 ID를 인증 정보에서 가져온다고 가정
        String memberId = getAuthenticatedMemberId();

        Review review = Review.builder()
                .reviewId(UUID.randomUUID().toString())
                .memberId(memberId)
                .videoId(reviewRequestDTO.getVideoId())
                .content(reviewRequestDTO.getContent())
                .rating(reviewRequestDTO.getRating())
                .createdAt(new java.util.Date())
                .build();

        reviewMapper.insertReview(review);

        ReviewResponseVO reviewResponseVO = ReviewResponseVO.builder().reviewId(review.getReviewId())
                                            .memberId(review.getMemberId())
                                            .videoId(review.getVideoId()).content(review.getContent())
                                            .rating(review.getRating()).createdAt(review.getCreatedAt())
                                            .build();
        return reviewResponseVO;
    }

    // 특정 영상에 대한 리뷰 리스트 조회
    public List<ReviewResponseVO> getReviewsByVideoId(String videoId) {
        List<Review> reviews = reviewMapper.selectReviewsByVideoId(videoId);
        return reviews.stream()
                .map(ReviewResponseVO::new)
                .toList();
    }

    // 리뷰 수정
    @Transactional
    public ReviewResponseVO updateReview(String reviewId, ReviewRequestDTO reviewRequestDTO) {
        Review review = reviewMapper.selectReviewById(reviewId);
        if (review == null) {
            throw new RuntimeException("Review not found");
        }

        review.setContent(reviewRequestDTO.getContent());
        review.setVideoId(reviewRequestDTO.getVideoId());
        review.setRating(reviewRequestDTO.getRating());

        reviewMapper.updateReview(review);
        return new ReviewResponseVO(review);
    }

    // 리뷰 삭제
    @Transactional
    public void deleteReview(String reviewId) {
        reviewMapper.deleteReview(reviewId);
    }

    // 인증된 사용자 ID 가져오기
    public String getAuthenticatedMemberId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof String)) {
            throw new MemberNotAuthenticatedException("Member not authenticated");
        }
        return (String) authentication.getPrincipal();
    }
}
