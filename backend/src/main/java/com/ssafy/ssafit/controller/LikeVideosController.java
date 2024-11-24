package com.ssafy.ssafit.controller;

import com.ssafy.ssafit.domain.LikeVideos;
import com.ssafy.ssafit.dto.request.LikeRequestDTO;
import com.ssafy.ssafit.dto.response.LikeResponseVO;
import com.ssafy.ssafit.service.LikeVideosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/likes")
public class LikeVideosController {

    private final LikeVideosService likeVideosService;

    public LikeVideosController(LikeVideosService likeVideosService) {
        this.likeVideosService = likeVideosService;
    }

    // 좋아요 등록
    @PostMapping
    public ResponseEntity<LikeResponseVO> addLike(@RequestBody LikeRequestDTO requestDTO) {
        String memberId = requestDTO.getMemberId();
        String videoId = requestDTO.getVideoId();
        LikeResponseVO likeResponseVO = likeVideosService.addLike(memberId, videoId);
        return ResponseEntity.ok(likeResponseVO);
    }

    // 특정 멤버의 좋아요 조회
    @GetMapping("/list/{memberId}")
    public ResponseEntity<List<LikeVideos>> getLikesByMember(@PathVariable String memberId) {
        List<LikeVideos> likes = likeVideosService.getLikesByMember(memberId);
        return ResponseEntity.ok(likes);
    }

    // 특정 영상의 좋아요 조회
    @GetMapping("/{videoId}")
    public ResponseEntity<List<LikeVideos>> getLikesByVideo(@PathVariable String videoId) {
        List<LikeVideos> likes = likeVideosService.getLikesByVideo(videoId);
        return ResponseEntity.ok(likes);
    }

    // 좋아요 삭제
    @DeleteMapping("/{likeId}")
    public ResponseEntity<Void> removeLike(@PathVariable Long likeId) {
        likeVideosService.removeLike(likeId);
        return ResponseEntity.noContent().build();
    }
}
