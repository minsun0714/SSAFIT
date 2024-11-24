package com.ssafy.ssafit.dto.request;

import com.ssafy.ssafit.domain.Part;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VideoRegistDTO {
    private String videoUrl;
    private Part part;
    private String introduceText;
}