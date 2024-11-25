package com.ssafy.ssafit.dto.request;

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
public class LikeRequestDTO {
    private Long likeId;
    private String memberId;
    private String videoId;
    private Date likedAt;
}