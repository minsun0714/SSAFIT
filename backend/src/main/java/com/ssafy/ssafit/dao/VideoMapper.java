package com.ssafy.ssafit.dao;

import com.ssafy.ssafit.domain.Video;
import com.ssafy.ssafit.dto.response.VideoCardVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface VideoMapper {
    // 비디오 등록
    void insertVideo(Video video);

    // 비디오 상세 정보 조회 - VideoDetail page
    Video findByVideoId(String videoId);

    // 비디오 전체 목록 조회 - VideoList Page
    List<VideoCardVO> findAllVideos(@Param("size") int size, @Param("offset") int offset);

   // 인기순 리스트 조회 (상위 8개) - main page
    List<VideoCardVO> findTop8ByViewCount();

    // 인기순 전체 목록 조회 - VideoList page
    List<VideoCardVO> findAllByViewCount();

    // 별점순 리스트 조회 (상위 3개) - main page
    List<VideoCardVO> findTop3ByRating();

    // 별점순 전체 목록 조회 - VideoList page
    List<VideoCardVO> findAllByRating();

    // 최신순 리스트 조회 (상위 3개) - main page
    List<VideoCardVO> findTop3ByLatest();

    // 최신순 전체 목록 조회 - VideoList page
    List<VideoCardVO> findAllByLatest();

    // 비디오 검색 (제목, 채널 이름 기반) - VideoList page
    List<VideoCardVO> searchByKeyword(@Param("keyword") String keyword);

    // admin에서 pending 상태인 비디오 조회
    List<VideoCardVO> selectPendingVideos(int page);
    // admin에서 비디오 승인
    void updateStatusToApproved(String videoId);

    // admin에서 비디오 거절
    void updateStatusToRejected(String videoId);
}