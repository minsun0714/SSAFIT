package com.ssafy.ssafit.service;

import com.ssafy.ssafit.dao.VideoMapper;
import com.ssafy.ssafit.domain.Video;
import com.ssafy.ssafit.dto.request.VideoRegistDTO;
import com.ssafy.ssafit.dto.response.VideoCardVO;
import com.ssafy.ssafit.dto.response.VideoDetailVO;
import com.ssafy.ssafit.dto.response.VideoRegistVO;
import com.ssafy.ssafit.exception.MemberNotAuthenticatedException;
import com.ssafy.ssafit.utils.YouTubeUtils;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VideoService {
    private final VideoMapper videoMapper;

    // 비디오 등록
    public VideoRegistVO registerVideo(VideoRegistDTO videoRegistDTO) throws ParseException, IOException, InterruptedException {
        String videoId = YouTubeUtils.extractVideoId(videoRegistDTO.getVideoUrl()); //url에서 id추출
        System.out.println(videoRegistDTO.toString());
        Video video = YouTubeUtils.loadVideoInfo(videoId);

        // 사용자 ID를 인증 정보에서 가져온다고 가정
        String memberId = getAuthenticatedMemberId();

        video = Video.builder()
                .videoId(video.getVideoId())
                .channelTitle(video.getChannelTitle())
                .title(video.getTitle())
                .viewCount(video.getViewCount())
                .publishedAt(video.getPublishedAt())
                .part(videoRegistDTO.getPart())
                .description(video.getDescription())
                .videoStatus(video.getVideoStatus())
                .memberId(memberId)
                .rating(video.getRating())
                .introduceText(videoRegistDTO.getIntroduceText())
                .build();

        System.out.println(video.toString());

        videoMapper.insertVideo(video); // DB에 video 정보 저장

        return VideoRegistVO.builder()
                .videoId(video.getVideoId())
                .channelTitle(video.getChannelTitle())
                .title(video.getTitle())
                .viewCount(video.getViewCount())
                .publishedAt(video.getPublishedAt())
                .part(videoRegistDTO.getPart())
                .description(video.getDescription())
                .videoStatus(video.getVideoStatus())
                .memberId(video.getMemberId())
                .rating(video.getRating())
                .introduceText(videoRegistDTO.getIntroduceText())
                .build();
    }

    // 비디오 상세 정보 조회
    public VideoDetailVO getVideoById(String videoId) {
        Video video = videoMapper.findByVideoId(videoId);
        System.out.println(video.toString());

        return VideoDetailVO.builder()
                .videoId(video.getVideoId())
                .channelTitle(video.getChannelTitle())
                .title(video.getTitle())
                .viewCount(video.getViewCount())
                .publishedAt(video.getPublishedAt())
                .part(video.getPart())
                .description(video.getDescription())
                .videoStatus(video.getVideoStatus())
                .memberId(video.getMemberId())
                .rating(video.getRating())
                .introduceText(video.getIntroduceText())
                .build();
    }
    //
    public List<VideoCardVO> getAllVideos(int page, int size) {
        int offset = page * size; // OFFSET 계산
        return videoMapper.findAllVideos(size, offset);
    }

    // 인기순 리스트 조회 (상위 8개)
    public List<Video> getTop8ByViewCount() {
        return videoMapper.findTop8ByViewCount();
    }

    // 인기순 전체 목록 조회
    public List<Video> getAllByViewCount() {
        return videoMapper.findAllByViewCount();
    }

    // 별점순 리스트 조회 (상위 3개)
    public List<Video> getTop3ByRating() {
        return videoMapper.findTop3ByRating();
    }

    // 별점순 전체 목록 조회
    public List<Video> getAllByRating() {
        return videoMapper.findAllByRating();
    }

    // 최신순 리스트 조회 (상위 3개)
    public List<Video> getTop3ByLatest() {
        return videoMapper.findTop3ByLatest();
    }

    // 최신순 전체 목록 조회
    public List<Video> getAllByLatest() {
        return videoMapper.findAllByLatest();
    }

    // 비디오 검색
    public List<Video> searchVideos(String keyword) {
        return videoMapper.searchByKeyword(keyword);
    }

    // admin에서 pending 상태인 비디오 조회
    public List<Video> getPendingVideos(int page) { return videoMapper.selectPendingVideos(page); }

    // admin에서 승인
    public void updateStatusToApproved(String videoId) { videoMapper.updateStatusToApproved(videoId);}

    // admin에서 거절
    public void updateStatusToRejected(String videoId) { videoMapper.updateStatusToRejected(videoId);}

    // 인증된 사용자 ID 가져오기
    public String getAuthenticatedMemberId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof String)) {
            throw new MemberNotAuthenticatedException("Member not authenticated");
        }
        return (String) authentication.getPrincipal();
    }
}
