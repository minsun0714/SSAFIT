package com.ssafy.ssafit.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ssafy.ssafit.domain.Video;
import com.ssafy.ssafit.domain.VideoStatus;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    //유튜브 api 활용해 다른 정보들 가져와 Video 객체 생성하는 메서드
    public static Video loadVideoInfo(String videoId) throws ParseException, IOException, InterruptedException {
        String apiKey = "AIzaSyAPZkqgH44ts-FP6w_IFCO_Jogj9ExR0FM"; // API 키
//        String videoId = "a_80o2lDYec"; // YouTube 비디오 ID.

        String url = String.format(
                "https://www.googleapis.com/youtube/v3/videos?part=snippet,statistics&id=%s&key=%s",
                videoId, apiKey
        );

        // Create HttpClient
        HttpClient client = HttpClient.newHttpClient();

        // Create HttpRequest
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        // Send HttpRequest
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Parse JSON Response
        JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
        JsonObject item = jsonObject.getAsJsonArray("items").get(0).getAsJsonObject();
        JsonObject snippet = item.getAsJsonObject("snippet");
        JsonObject statistics = item.getAsJsonObject("statistics");

        // Extract fields from the response
        String title = snippet.get("title").getAsString();
        String channelTitle = snippet.get("channelTitle").getAsString();
        String publishedAt = snippet.get("publishedAt").getAsString();
        String thumbnailUrl = snippet.getAsJsonObject("thumbnails").getAsJsonObject("high").get("url").getAsString();
        Long viewCount = statistics.get("viewCount").getAsLong();
        String description = snippet.get("description").getAsString();

        // Generate embedding URL
        String embedUrl = "https://www.youtube.com/embed/" + videoId;
        // Convert publishedAt to Date
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date createdAt = formatter.parse(publishedAt);

        Video video = new Video();

        video.setVideoId(videoId);
        video.setChannelTitle(channelTitle);
        video.setTitle(title);
        video.setViewCount(viewCount);
        video.setPublishedAt(createdAt);
        video.setPart(null);
        video.setVideoStatus(VideoStatus.PENDING);
        video.setMemberId(null);
        video.setRating(0);
        video.setIntroduceText(null);
        video.setDescription(description);

        return video;
    }

    //임베딩 url 추출
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
