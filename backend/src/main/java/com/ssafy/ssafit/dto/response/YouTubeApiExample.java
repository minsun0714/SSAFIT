package com.ssafy.ssafit.dto.response;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class YouTubeApiExample {
    public static void main(String[] args) throws Exception {
        String apiKey = "AIzaSyAPZkqgH44ts-FP6w_IFCO_Jogj9ExR0FM"; // API 키를 여기에 입력하세요.
        String videoId = "a_80o2lDYec"; // 분석할 YouTube 비디오 ID.

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
        String viewCount = statistics.get("viewCount").getAsString();

        // Generate embedding URL
        String embedUrl = "https://www.youtube.com/embed/" + videoId;

        // Print the extracted information
        System.out.println("Title: " + title);
        System.out.println("Channel: " + channelTitle);
        System.out.println("Published At: " + publishedAt);
        System.out.println("Thumbnail URL: " + thumbnailUrl);
        System.out.println("View Count: " + viewCount);
        System.out.println("Embedding URL: " + embedUrl);
    }
}
