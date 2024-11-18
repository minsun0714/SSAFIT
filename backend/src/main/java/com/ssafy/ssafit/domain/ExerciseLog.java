package com.ssafy.ssafit.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExerciseLog {
    private Long exerciseLogId;
    private String memberId;
    private Date exerciseDate;
    private String exerciseType;
    private int exerciseTime;
    private double caloriesBurned;
    private double fatBurned;
}
