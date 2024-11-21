package com.ssafy.ssafit.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.net.MalformedURLException;

import java.net.URL;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Video {
    private String videoId;
    private Member member;
    private String title;
    private int viewCount;
    private Date createdAt;
    private String part;
    private String introduceText;
    private String thumbnailImgUrl;
    private String videoUrl;
    private String status;
    private double rating;

    public URL getThumbnailImgUrlAsUrl() throws MalformedURLException {
        return new URL(thumbnailImgUrl);
    }

    public URL getVideoUrlAsUrl() throws MalformedURLException {
        return new URL(videoUrl);
    }
}
