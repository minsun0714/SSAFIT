package com.ssafy.ssafit.domain;

import com.ssafy.ssafit.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {
    private String reviewId;
    private String memberId;
    private String videoId;
    private Date createdAt;
    private String content;
    private Double rating;
}