package com.ssafy.ssafit.controller;

import com.ssafy.ssafit.domain.LikeVideos;
import com.ssafy.ssafit.dto.request.LikeRequestDTO;
import com.ssafy.ssafit.dto.response.LikeResponseVO;
import com.ssafy.ssafit.dto.response.VideoCardVO;
import com.ssafy.ssafit.exception.MemberNotAuthenticatedException;
import com.ssafy.ssafit.service.LikeVideosService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/likes")
public class LikeVideosController {

    private final LikeVideosService likeVideosService;

    public LikeVideosController(LikeVideosService likeVideosService) {
        this.likeVideosService = likeVideosService;
    }

    // 좋아요 추가
    @PostMapping
    public ResponseEntity<LikeResponseVO> addLike(@RequestBody LikeRequestDTO requestDTO) {
        String memberId = getAuthenticatedMemberId();
        LikeResponseVO response = likeVideosService.addLike(memberId, requestDTO.getVideoId());
        return ResponseEntity.ok(response);
    }

    // 나의 좋아요 조회
    @GetMapping("/list")
    public ResponseEntity<List<VideoCardVO>> getLikes() {
        String memberId = getAuthenticatedMemberId();
        List<VideoCardVO> likes = likeVideosService.getLikesByMember(memberId);
        return ResponseEntity.ok(likes);
    }

    // 특정 멤버의 좋아요 조회
    @GetMapping("/list/{memberId}")
    public ResponseEntity<List<VideoCardVO>> getLikesByMember(@PathVariable String memberId) {
        List<VideoCardVO> likes = likeVideosService.getLikesByMember(memberId);
        return ResponseEntity.ok(likes);
    }

    // 특정 영상의 좋아요 조회
    @GetMapping("/{videoId}")
    public ResponseEntity<List<LikeVideos>> getLikesByVideo(@PathVariable String videoId) {
        List<LikeVideos> likes = likeVideosService.getLikesByVideo(videoId);
        return ResponseEntity.ok(likes);
    }

    // 좋아요 삭제
    @DeleteMapping("/{videoId}")
    public ResponseEntity<Void> removeLikeByVideoId(@PathVariable String videoId) {
        String memberId = getAuthenticatedMemberId();
        likeVideosService.removeLikeByVideoId(memberId, videoId);
        return ResponseEntity.noContent().build();
    }

    // 인증된 사용자 ID 가져오기
    private String getAuthenticatedMemberId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof String)) {
            throw new MemberNotAuthenticatedException("Member not authenticated");
        }
        return (String) authentication.getPrincipal();
    }
}