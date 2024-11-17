package com.ssafy.ssafit.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.ssafit.domain.ExerciseType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExerciseInfoRequestDTO {

    @NotNull(message = "Exercise date is required.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date exerciseDate;

    @NotNull(message = "Exercise type is required.")
    private ExerciseType exerciseType;

    @Positive(message = "Exercise time must be greater than 0.")
    private int exerciseTime;
}
