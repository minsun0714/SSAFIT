package com.ssafy.ssafit.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class ExerciseMetData {
    @JsonProperty("운동명") // JSON 필드명과 매핑
    private String exerciseType;

    @JsonProperty("MET 계수")
    private double met;
}
