package com.ssafy.ssafit.controller;

import com.ssafy.ssafit.dto.request.SignUpRequestDTO;
import com.ssafy.ssafit.dto.request.VideoRegistDTO;
import com.ssafy.ssafit.dto.response.SignUpResponseDTO;
import com.ssafy.ssafit.dto.response.VideoRegistVO;
import com.ssafy.ssafit.service.VideoService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/videos")

public class VideoController {

    private final VideoService videoService;

    //비디오 등록
    @PostMapping
    public ResponseEntity<VideoRegistVO> createUser(@Valid @RequestBody VideoRegistDTO videoRegistDTO) throws ParseException, IOException, InterruptedException {
        VideoRegistVO registeredVideo = videoService.registerVideo(videoRegistDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredVideo);
    }
}
