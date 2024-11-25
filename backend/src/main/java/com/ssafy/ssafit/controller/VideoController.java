package com.ssafy.ssafit.controller;

import com.ssafy.ssafit.domain.Video;
import com.ssafy.ssafit.dto.request.VideoRegistDTO;
import com.ssafy.ssafit.dto.response.MemberInfoResponseDTO;
import com.ssafy.ssafit.dto.response.VideoCardVO;
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
import java.util.List;

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

    // 비디오 전체 목록 조회
    @GetMapping("/list")
    public ResponseEntity<List<VideoCardVO>> getAllVideos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "9") int size) {
        List<VideoCardVO> videos = videoService.getAllVideos(page, size);
        return ResponseEntity.ok(videos);
    }

    // 인기순 리스트 조회 (상위 8개)
    @GetMapping("/top8/view-count")
    public ResponseEntity<List<VideoCardVO>> getTop8ByViewCount() {
        List<VideoCardVO> videos = videoService.getTop8ByViewCount();
        return ResponseEntity.ok(videos);
    }

    // 인기순 전체 목록 조회
    @GetMapping("/list/view-count")
    public ResponseEntity<List<VideoCardVO>> getAllByViewCount() {
        List<VideoCardVO> videos = videoService.getAllByViewCount();
        return ResponseEntity.ok(videos);
    }

    // 별점순 리스트 조회 (상위 3개)
    @GetMapping("/top3/rating")
    public ResponseEntity<List<VideoCardVO>> getTop3ByRating() {
        List<VideoCardVO> videos = videoService.getTop3ByRating();
        return ResponseEntity.ok(videos);
    }

    // 별점순 전체 목록 조회
    @GetMapping("/list/rating")
    public ResponseEntity<List<VideoCardVO>> getAllByRating() {
        List<VideoCardVO> videos = videoService.getAllByRating();
        return ResponseEntity.ok(videos);
    }

    // 최신순 리스트 조회 (상위 3개)
    @GetMapping("/top3/latest")
    public ResponseEntity<List<VideoCardVO>> getTop3ByLatest() {
        List<VideoCardVO> videos = videoService.getTop3ByLatest();
        return ResponseEntity.ok(videos);
    }

    // 최신순 전체 목록 조회
    @GetMapping("/list/latest")
    public ResponseEntity<List<VideoCardVO>> getAllByLatest() {
        List<VideoCardVO> videos = videoService.getAllByLatest();
        return ResponseEntity.ok(videos);
    }

    // 비디오 검색
    @GetMapping("/search")
    public ResponseEntity<List<VideoCardVO>> searchVideos(@RequestParam String keyword) {
        List<VideoCardVO> videos = videoService.searchVideos(keyword);
        return ResponseEntity.ok(videos);
    }

}
