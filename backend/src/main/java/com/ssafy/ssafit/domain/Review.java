package com.ssafy.ssafit.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Review {
    private String reviewId;
    private String memberId;
    private String videoId;
    private Date createdAt;
    private String content;
    private int star;
}
