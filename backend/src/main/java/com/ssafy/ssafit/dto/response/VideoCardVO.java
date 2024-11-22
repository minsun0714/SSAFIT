package com.ssafy.ssafit.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VideoCardVO {
    private String videoId;
    private String channel;
    private String member;
    private String title;
    private int viewCount;
    private Date createdAt;
    private String part;
    private String status;

    private String introduceText;
    private double rating;
}