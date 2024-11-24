package com.ssafy.ssafit.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewRequestDTO {
    private String memberId;  // 수정: Member 대신 memberId 사용
    private String videoId;
    private String content;
    private Double rating;  // 수정: Double 타입으로 변경
}