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
public class Video {
    private String videoId;
    private String channelTitle;
    private String title;
    private Long viewCount;
    private Date publishedAt;
    private Part part;
    private VideoStatus videoStatus;

    private String memberId;
    private double rating;
    private String introduceText;
    private String description;

    private String embeddingUrl;
    private String thumbnailUrl;
}
