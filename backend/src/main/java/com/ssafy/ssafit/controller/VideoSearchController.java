package com.ssafy.ssafit.controller;

import com.ssafy.ssafit.domain.VideoSortType;
import com.ssafy.ssafit.dto.request.VideoRegistDTO;
import com.ssafy.ssafit.dto.response.PagedResponseDTO;
import com.ssafy.ssafit.dto.response.VideoCardVO;
import com.ssafy.ssafit.dto.response.VideoRegistVO;
import com.ssafy.ssafit.service.VideoSearchService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/video/search")
public class VideoSearchController {

    private final VideoSearchService videoSearchService;

    // 비디오 검색 페이지네이션 + 소팅 + 검색
    @PostMapping
    public ResponseEntity<PagedResponseDTO<VideoCardVO>> getPaginatedAndSortedVideos(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) int page,
            @RequestParam(required = false) int size,
            @RequestParam(required = false) VideoSortType sort
    ) {
        PagedResponseDTO<VideoCardVO> videoCardVOPagedResponseDTO = videoSearchService.getPaginatedAndSortedVideos(keyword, page, size, sort);
        return ResponseEntity.ok().build();
    }
}
