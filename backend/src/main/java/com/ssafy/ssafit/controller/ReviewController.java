package com.ssafy.ssafit.controller;

import com.ssafy.ssafit.dto.request.ReviewRequestDTO;
import com.ssafy.ssafit.dto.response.ReviewResponseVO;
import com.ssafy.ssafit.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 생성
    @PostMapping
    public ResponseEntity<ReviewResponseVO> createReview(@RequestBody ReviewRequestDTO reviewRequestDTO) {
        ReviewResponseVO createdReview = reviewService.createReview(reviewRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReview);
    }

    // 리뷰 조회 (특정 영상에 대한 리뷰 리스트)
    @GetMapping("/video/{videoId}")
    public ResponseEntity<List<ReviewResponseVO>> getReviewsByVideoId(@PathVariable String videoId) {
        List<ReviewResponseVO> reviews = reviewService.getReviewsByVideoId(videoId);
        return ResponseEntity.ok(reviews);
    }

    // 리뷰 조회 (특정 유저의 리뷰 리스트)
    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<ReviewResponseVO>> getReviewsByMemberId(@PathVariable String memberId) {
        List<ReviewResponseVO> reviews = reviewService.getReviewsByMemberId(memberId);
        return ResponseEntity.ok(reviews);
    }

    // 리뷰 조회 (나의 리뷰 리스트)
    @GetMapping("/member")
    public ResponseEntity<List<ReviewResponseVO>> getReviewsByMyId() {
        List<ReviewResponseVO> reviews = reviewService.getReviewsByMyId();
        return ResponseEntity.ok(reviews);
    }

    // 리뷰 수정
    @PutMapping("/{reviewId}")
    public ResponseEntity<ReviewResponseVO> updateReview(@PathVariable String reviewId,
                                                          @RequestBody ReviewRequestDTO reviewRequestDTO) {
        ReviewResponseVO updatedReview = reviewService.updateReview(reviewId, reviewRequestDTO);
        return ResponseEntity.ok(updatedReview);
    }

    // 리뷰 삭제
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable String reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }
}
