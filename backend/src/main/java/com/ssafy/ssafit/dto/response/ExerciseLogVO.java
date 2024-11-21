package com.ssafy.ssafit.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExerciseLogVO {
    private Long exerciseLogId;
    private Date exerciseDate;
    private String exerciseType;
    private int exerciseTime;
    private double caloriesBurned;
    private double fatBurned;
}
