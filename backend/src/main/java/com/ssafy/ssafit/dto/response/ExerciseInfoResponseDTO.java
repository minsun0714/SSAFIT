package com.ssafy.ssafit.dto.response;

import com.ssafy.ssafit.domain.ExerciseType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExerciseInfoResponseDTO {
    private Long exerciseLogId;
    private Date exerciseDate;
    private String exerciseType;
    private int exerciseTime;
    private Long caloriesBurned;
    private Long fatBurned;
}
