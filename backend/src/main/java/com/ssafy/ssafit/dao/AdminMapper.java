package com.ssafy.ssafit.dao;

import com.ssafy.ssafit.domain.Video;
import com.ssafy.ssafit.domain.VideoStatus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {

    // PENDING 상태 비디오 목록 조회
    List<Video> findPendingVideos(@Param("offset") int offset, @Param("size") int size);

    // PENDING 상태 비디오 총 개수 조회
    int countPendingVideos();

    // 비디오 상태 업데이트
    void updateVideoStatus(@Param("videoId") String videoId, @Param("status") VideoStatus status);
}
