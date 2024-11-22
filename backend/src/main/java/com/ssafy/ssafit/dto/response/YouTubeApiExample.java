package com.ssafy.ssafit.dto.response;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ssafy.ssafit.domain.Member;
import com.ssafy.ssafit.domain.Part;
import com.ssafy.ssafit.domain.Video;
import com.ssafy.ssafit.domain.VideoStatus;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

public class YouTubeApiExample {
    public static void main(String[] args) throws Exception {
        String apiKey = "AIzaSyAPZkqgH44ts-FP6w_IFCO_Jogj9ExR0FM"; // API 키
        String videoId = "a_80o2lDYec"; // YouTube 비디오 ID.

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
        video.setMember(null);
        video.setRating(1);
        video.setIntroduceText(null);
        video.setDescription(description);

        System.out.println(video.toString());
    }
}
