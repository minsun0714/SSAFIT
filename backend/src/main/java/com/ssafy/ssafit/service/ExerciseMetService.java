package com.ssafy.ssafit.service;

import com.ssafy.ssafit.domain.ExerciseMetData;
import com.ssafy.ssafit.dto.response.ExerciseMetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class ExerciseMetService {
    private final WebClient webClient;

    @Value("${api.base-url}")
    private String url;

    @Value("${api.service-key}")
    private String serviceKey;

    @Value("${api.authorization-header}")
    private String authorizationHeader;

    // MET 데이터를 가져오는 메서드
    public ExerciseMetData getMetData(String exerciseType) {
        ExerciseMetResponse response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(url)
                        .queryParam("page", 1)
                        .queryParam("perPage", 600)
                        .queryParam("serviceKey", serviceKey)
                        .build())
                .header("Authorization", authorizationHeader)
                .retrieve()
                .bodyToMono(ExerciseMetResponse.class)
                .block();

        // 운동 종류에 해당하는 MET 데이터 필터링
        return response.getData().stream()
                .filter(data -> data.getExerciseType().equalsIgnoreCase(exerciseType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("MET 데이터가 없습니다."));
    }
}
