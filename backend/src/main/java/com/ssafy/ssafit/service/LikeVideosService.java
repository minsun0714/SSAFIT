package com.ssafy.ssafit.service;

import com.ssafy.ssafit.dao.LikeVideosMapper;
import com.ssafy.ssafit.domain.LikeVideos;
import com.ssafy.ssafit.dto.response.LikeResponseVO;
import com.ssafy.ssafit.dto.response.VideoCardVO;
import com.ssafy.ssafit.exception.MemberNotAuthenticatedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class LikeVideosService {

    private final LikeVideosMapper likeVideosMapper;

    public LikeVideosService(LikeVideosMapper likeVideosMapper) {
        this.likeVideosMapper = likeVideosMapper;
    }

    // 좋아요 추가
    @Transactional
    public LikeResponseVO addLike(String memberId, String videoId) {
        if (!likeVideosMapper.existsByMemberIdAndVideoId(memberId, videoId)) {
            LikeVideos like = LikeVideos.builder()
                    .memberId(memberId)
                    .videoId(videoId)
                    .likedAt(new Date())
                    .build();
            likeVideosMapper.insertLike(like);
            return LikeResponseVO.builder()
                    .memberId(memberId)
                    .videoId(videoId)
                    .likedAt(like.getLikedAt())
                    .build();
        }
        return null; // 이미 좋아요가 존재할 경우
    }

    // 특정 멤버의 좋아요 조회
    public List<VideoCardVO> getLikesByMember(String memberId) {
        return likeVideosMapper.findByMemberId(memberId);
    }

    // 특정 영상의 좋아요 조회
    public List<LikeVideos> getLikesByVideo(String videoId) {
        return likeVideosMapper.findByVideoId(videoId);
    }

    // 좋아요 삭제
    @Transactional
    public void removeLikeByVideoId(String memberId, String videoId) {
        likeVideosMapper.deleteByVideoId(memberId, videoId);
    }

    // 인증된 사용자 ID 가져오기
    public String getAuthenticatedMemberId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof String)) {
            throw new MemberNotAuthenticatedException("Member not authenticated");
        }
        return (String) authentication.getPrincipal();
    }
}
