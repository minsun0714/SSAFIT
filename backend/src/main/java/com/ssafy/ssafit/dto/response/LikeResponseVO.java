package com.ssafy.ssafit.dto.response;

import com.ssafy.ssafit.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikeResponseVO {
    private Long likeId;
    private Member member;
    private String videoId;
    private Date likedAt;
}