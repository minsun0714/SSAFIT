package com.ssafy.ssafit.dto.request;

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
public class ReviewRegistDTO {
    private String reviewId;
    private Member member;
    private String videoId;
    private Date createdAt;
    private String content;

}
