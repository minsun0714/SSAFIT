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
public class LikeVideos {
    private Long likeId;
    private Member member;
    private String videoId;
    private Date likedAt;
}