package com.ssafy.ssafit.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExerciseInfoResponseDTO {
    private List<ExerciseLogVO> exerciseLogVO;

    private Long dailyTotalExerciseTime;
}
