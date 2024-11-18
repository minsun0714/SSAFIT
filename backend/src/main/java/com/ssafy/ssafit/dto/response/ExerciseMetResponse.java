package com.ssafy.ssafit.dto.response;

import com.ssafy.ssafit.domain.ExerciseMetData;
import lombok.Data;

import java.util.List;

@Data
public class ExerciseMetResponse {
    private List<ExerciseMetData> data;
}