package com.ssafy.ssafit.dto.response;

import com.ssafy.ssafit.domain.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExerciseGrassVO {
    private String date;
    private int level;
    private int exerciseTime;
}
