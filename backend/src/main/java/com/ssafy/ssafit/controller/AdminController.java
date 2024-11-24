package com.ssafy.ssafit.controller;

import com.ssafy.ssafit.domain.VideoStatus;
import com.ssafy.ssafit.dto.response.PagedResponseDTO;
import com.ssafy.ssafit.dto.response.VideoCardVO;
import com.ssafy.ssafit.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/videos/pending")
    public ResponseEntity<PagedResponseDTO<VideoCardVO>> getPendingVideos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PagedResponseDTO<VideoCardVO> pendingVideos = adminService.getPendingVideos(page, size);
        return ResponseEntity.ok(pendingVideos);
    }

    @PatchMapping("/videos/{videoId}")
    public ResponseEntity<Void> updateVideoStatus(
            @PathVariable String videoId,
            @RequestBody Map<String, String> body
    ) {
        String status = body.get("status");
        adminService.updateVideoStatus(videoId, VideoStatus.valueOf(status));
        return ResponseEntity.ok().build();
    }
}
