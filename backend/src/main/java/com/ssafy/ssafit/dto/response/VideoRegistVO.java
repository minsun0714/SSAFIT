package com.ssafy.ssafit.dto.response;

import com.ssafy.ssafit.domain.Member;
import com.ssafy.ssafit.domain.Part;
import com.ssafy.ssafit.domain.VideoStatus;
import com.ssafy.ssafit.dto.response.VideoCardVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class VideoRegistVO extends VideoCardVO {
    private Member member;
    private double rating;
    private String introduceText;
    private String description;

    public VideoRegistVO(
            String videoId,
            String channelTitle,
            String title,
            Long viewCount,
            Date publishedAt,
            Part part,
            VideoStatus videoStatus,
            Member member,
            String introduceText,
            String description,
            double rating
    ) {
        super(videoId, channelTitle, title, viewCount, publishedAt, part, videoStatus);
        this.member = member;
        this.introduceText = introduceText;
        this.description = description;
        this.rating = rating;
    }
}
