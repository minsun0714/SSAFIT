package com.ssafy.ssafit.dto.response;

import com.ssafy.ssafit.domain.Part;
import com.ssafy.ssafit.domain.VideoStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.net.MalformedURLException;

import java.net.URL;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class VideoCardVO {
    private String videoId;
    private String channelTitle;
    private String title;
    private Long viewCount;
    private Date publishedAt;
    private Part part;
    private VideoStatus videoStatus;
}