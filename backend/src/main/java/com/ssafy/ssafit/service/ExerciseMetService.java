package com.ssafy.ssafit.service;

import com.ssafy.ssafit.domain.ExerciseMetData;
import com.ssafy.ssafit.dto.response.ExerciseMetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    // MET 데이터를 가져오는 메서드


    public List<ExerciseMetData> getMetDataByPage(int page, int perPage, String exerciseType) {
        System.out.println(exerciseType);
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

        if (response == null || response.getData() == null) {
            throw new IllegalArgumentException("데이터를 가져올 수 없습니다.");
        }

        // exerciseType 필터링
        List<ExerciseMetData> filteredData = (exerciseType == null || exerciseType.trim().isEmpty())
                ? response.getData()
                : response.getData().stream()
                .filter(data -> data.getExerciseType().toLowerCase().contains(exerciseType.toLowerCase()))
                .collect(Collectors.toList());
        System.out.println(filteredData);

        // page와 perPage로 데이터 자르기
        int startIndex = (page - 1) * perPage;
        int endIndex = Math.min(startIndex + perPage, filteredData.size());

        if (startIndex >= filteredData.size() || startIndex < 0) {
            return Collections.emptyList(); // 페이지 범위를 벗어난 경우 빈 리스트 반환
        }

        return filteredData.subList(startIndex, endIndex);
    }


    public int getTotalDataCount(String exerciseType) {
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

        if (response == null || response.getData() == null) {
            throw new IllegalArgumentException("총 데이터 개수를 가져올 수 없습니다.");
        }

        List<ExerciseMetData> filteredData = (exerciseType == null || exerciseType.trim().isEmpty())
                ? response.getData()
                : response.getData().stream()
                .filter(data -> data.getExerciseType().toLowerCase().contains(exerciseType.toLowerCase()))
                .toList();

        return filteredData.size();
    }
}
