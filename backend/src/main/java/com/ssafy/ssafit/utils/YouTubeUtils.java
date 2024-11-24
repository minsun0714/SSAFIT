package com.ssafy.ssafit.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YouTubeUtils {

    //videoId 입력 받은 값에서 유튜브 api 활용해 video에 정보 넣어 return해주는 코드
    public static Video fetchVideoDetails(String videoId, String apiKey) throws Exception {
        if (videoId == null || videoId.isEmpty() || apiKey == null || apiKey.isEmpty()) {
            throw new IllegalArgumentException("Video ID와 API Key는 반드시 필요합니다.");
        }

        String url = String.format(
                "https://www.googleapis.com/youtube/v3/videos?part=snippet,statistics&id=%s&key=%s",
                videoId, apiKey
        );

        // HTTP Client 생성
        HttpClient client = HttpClient.newHttpClient();

        // HTTP Request 생성
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        // HTTP Request 보내고 응답 받기
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // JSON 응답 파싱
        JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
        JsonObject item = jsonObject.getAsJsonArray("items").get(0).getAsJsonObject();
        JsonObject snippet = item.getAsJsonObject("snippet");
        JsonObject statistics = item.getAsJsonObject("statistics");

        // 데이터 추출
        String title = snippet.get("title").getAsString();
        String channelTitle = snippet.get("channelTitle").getAsString();
        String publishedAt = snippet.get("publishedAt").getAsString();
        String description = snippet.get("description").getAsString();
        Long viewCount = statistics.get("viewCount").getAsLong();

        // 썸네일 URL 생성
        String thumbnailUrl = snippet.getAsJsonObject("thumbnails")
                .getAsJsonObject("high")
                .get("url").getAsString();

        // 발행일을 Date 객체로 변환
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date createdAt = formatter.parse(publishedAt);

        // Video 객체 생성
        Video video = new Video();
        video.setVideoId(videoId);
        video.setTitle(title);
        video.setChannelTitle(channelTitle);
        video.setViewCount(viewCount);
        video.setPublishedAt(createdAt);
        video.setDescription(description);
        video.setVideoStatus(VideoStatus.PENDING);
        video.setThumbnailUrl(thumbnailUrl); // 썸네일 URL 저장

        return video;
    }

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
