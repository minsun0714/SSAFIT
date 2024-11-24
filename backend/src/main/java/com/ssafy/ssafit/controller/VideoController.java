package com.ssafy.ssafit.controller;

import com.ssafy.ssafit.domain.Video;
import com.ssafy.ssafit.dto.request.VideoRegistDTO;
import com.ssafy.ssafit.dto.response.MemberInfoResponseDTO;
import com.ssafy.ssafit.dto.response.VideoDetailVO;
import com.ssafy.ssafit.dto.response.VideoRegistVO;
import com.ssafy.ssafit.service.VideoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // 비디오 상세 정보 조회
    @GetMapping("/{videoId}")
    public ResponseEntity<VideoDetailVO> getVideoById(@PathVariable String videoId) {
        VideoDetailVO videoDetail = videoService.getVideoById(videoId);
        return ResponseEntity.ok(videoDetail);
    }


}
