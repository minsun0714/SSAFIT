package com.ssafy.ssafit.dto.response;

import com.ssafy.ssafit.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewResponseVO {
    private String reviewId;
    private String memberId;
    private String videoId;
    private String content;
    private Date createdAt;
    private Double rating;

    public ReviewResponseVO(Review review) {
        this.reviewId = review.getReviewId();
        this.memberId = review.getMemberId();
        this.videoId = review.getVideoId();
        this.content = review.getContent();
        this.createdAt = review.getCreatedAt();
        this.rating = review.getRating();
    }
}
