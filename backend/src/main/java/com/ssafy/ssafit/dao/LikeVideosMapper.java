package com.ssafy.ssafit.dao;

import com.ssafy.ssafit.domain.LikeVideos;

import java.util.List;
import java.util.Optional;

public interface LikeVideosMapper {

    // 좋아요 추가
    void insertLike(LikeVideos likeVideos);

    // 특정 멤버의 좋아요 조회
    List<LikeVideos> findByMemberId(String memberId);

    // 특정 영상에 대한 좋아요 조회
    List<LikeVideos> findByVideoId(String videoId);

    // 특정 좋아요 조회
    Optional<LikeVideos> findByLikeId(Long likeId);

    // 좋아요 삭제 (likeId 기준)
    void deleteByLikeId(Long likeId);

    // 특정 멤버와 영상의 좋아요 여부 확인
    boolean existsByMemberIdAndVideoId(String memberId, String videoId);
}
