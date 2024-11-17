package com.ssafy.ssafit.service;

import com.ssafy.ssafit.domain.ExerciseMetData;
import com.ssafy.ssafit.dto.response.ExerciseMetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class ExerciseMetService {
    private final WebClient webClient;

    // MET 데이터를 가져오는 메서드
    public ExerciseMetData getMetData(String exerciseType) {
        String url = "/15068730/v1/uddi:12fe14fb-c8ca-47b1-9e53-97a93cb214ed";
        ExerciseMetResponse response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(url)
                        .queryParam("page", 1)
                        .queryParam("perPage", 600)
                        .queryParam("serviceKey", "h0ergBysleR4ddRKlvZEsw1Zcj6cD9hgAeWybUBCovf12SC6ZMFsxD0oQ%2FYQvT6pYprS8iSv0%2BVlHla4s8SIpQ%3D%3D")
                        .build())
                .header("Authorization", "Infuser h0ergBysleR4ddRKlvZEsw1Zcj6cD9hgAeWybUBCovf12SC6ZMFsxD0oQ/YQvT6pYprS8iSv0+VlHla4s8SIpQ==")
                .retrieve()
                .bodyToMono(ExerciseMetResponse.class)
                .block();
        System.out.println(response);

        // 운동 종류에 해당하는 MET 데이터 필터링
        return response.getData().stream()
                .filter(data -> data.getExerciseType().equalsIgnoreCase(exerciseType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("MET 데이터가 없습니다."));
    }
}
