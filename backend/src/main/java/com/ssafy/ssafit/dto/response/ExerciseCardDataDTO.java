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
public class ExerciseCardDataDTO {
    private String title;
    private String cardIconUrl;
    private CardType cardType;
    private double currentValue;
    private double lastValue;
    private boolean isUp;
}
