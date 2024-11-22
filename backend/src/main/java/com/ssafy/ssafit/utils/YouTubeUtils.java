package com.ssafy.ssafit.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YouTubeUtils {

    //입력받은 url 주소에서 id 빼오는 코드
    public static String extractVideoId(String videoUrl) {
        if (videoUrl == null || videoUrl.isEmpty()) {
            return null;
        }

        String regex = "((?:v=|youtu\\.be/)([a-zA-Z0-9_-]{11}))";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(videoUrl);

        if (matcher.find()) {
            return matcher.group(2);
        }

        return null;
    }

    //임베딩 ul 추출
    public static String getEmbeddingUrl(String videoId) {
        if (videoId == null || videoId.isEmpty()) {
            return null;
        }
        return "https://www.youtube.com/embed/" + videoId;
    }

    //썸네일 이미지 URL 추출
    public static String getThumbnailUrl(String videoId) {
        if (videoId == null || videoId.isEmpty()) {
            return null;
        }
        return "https://img.youtube.com/vi/" + videoId + "/hqdefault.jpg";
    }
}
