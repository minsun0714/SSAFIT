package com.ssafy.ssafit.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VideoRegistDTO {
    private String videoUrl;
    private String part;
    private String introduceText;
}
