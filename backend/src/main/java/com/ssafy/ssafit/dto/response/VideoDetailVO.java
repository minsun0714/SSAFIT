package com.ssafy.ssafit.dto.response;

import com.ssafy.ssafit.domain.Part;
import com.ssafy.ssafit.domain.VideoStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class VideoDetailVO extends VideoCardVO {
    private String memberId;
    private double rating;
    private String introduceText;
    private String description;

    public VideoDetailVO(
            String videoId,
            String channelTitle,
            String title,
            Long viewCount,
            Date publishedAt,
            Part part,
            VideoStatus videoStatus,
            String memberId,
            String introduceText,
            String description,
            String thumbnailUrl,
            String embeddingUrl,
            double rating
    ) {
        super(videoId, channelTitle, title, viewCount, publishedAt, part, videoStatus, thumbnailUrl, embeddingUrl);
        this.memberId = memberId;
        this.introduceText = introduceText;
        this.description = description;
        this.rating = rating;
    }
}
