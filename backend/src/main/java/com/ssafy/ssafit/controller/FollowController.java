package com.ssafy.ssafit.controller;

import com.ssafy.ssafit.dto.response.FollowVO;
import com.ssafy.ssafit.dto.response.SignUpResponseDTO;
import com.ssafy.ssafit.service.FollowService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/follows")
@AllArgsConstructor
public class FollowController {

    private final FollowService followService;

    // 팔로잉 관계 생성
    @PostMapping
    public ResponseEntity<SignUpResponseDTO> createFollowing(@RequestParam String followerId) {
        followService.createFollow(followerId);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 팔로우 관계
    @GetMapping
    public ResponseEntity<FollowVO> getFollows(){
        FollowVO followVO = followService.getFollowRelationship();

        return ResponseEntity.ok(followVO);
    }

    // 언팔로우
    @DeleteMapping
    public ResponseEntity<Void> unfollow(@RequestParam String followerId){
        followService.unfollow(followerId);

        return ResponseEntity.ok().build();
    }
}
