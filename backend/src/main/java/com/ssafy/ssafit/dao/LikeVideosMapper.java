package com.ssafy.ssafit.dao;

import com.ssafy.ssafit.domain.LikeVideos;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface LikeVideosMapper {

    // 좋아요 추가
    void insertLike(LikeVideos likeVideos);

    // 특정 멤버의 좋아요 리스트 조회
    List<LikeVideos> findByMemberId(String memberId);

    // 특정 영상에 대한 좋아요 조회
    List<LikeVideos> findByVideoId(String videoId);

    // 특정 좋아요 조회 (likeId 기반)
    Optional<LikeVideos> findByLikeId(Long likeId);

    // 좋아요 삭제 (videoId 기반)
    void deleteByVideoId(@Param("memberId") String memberId, @Param("videoId") String videoId);

    // 특정 멤버와 영상의 좋아요 여부 확인
    boolean existsByMemberIdAndVideoId(@Param("memberId") String memberId, @Param("videoId") String videoId);
}
