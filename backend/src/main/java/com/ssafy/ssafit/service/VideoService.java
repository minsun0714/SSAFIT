package com.ssafy.ssafit.service;

import com.ssafy.ssafit.dao.VideoMapper;
import com.ssafy.ssafit.domain.Member;
import com.ssafy.ssafit.domain.Role;
import com.ssafy.ssafit.domain.Video;
import com.ssafy.ssafit.dto.request.VideoRegistDTO;
import com.ssafy.ssafit.dto.response.VideoRegistVO;
import com.ssafy.ssafit.utils.YouTubeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.ssafy.ssafit.utils.DTOMapper.toSignUpResponseDTO;

@Service
@RequiredArgsConstructor
public class VideoService {
    private final VideoMapper videoMapper;

    // 비디오 등록
    public void registerVideo(VideoRegistDTO videoRegistDTO) {
        Member member = Member.builder()
                .memberId(memberRequestDTO.getMemberId())
                .password(passwordEncoder.encode(memberRequestDTO.getPassword())) // 보안상 해시 처리 필요
                .name(memberRequestDTO.getName())
                .nickname(memberRequestDTO.getNickname())
                .role(Role.ROLE_ADMIN)
                .build();

        String videoId = videoRegistDTO.getVideoUrl();
        String apiKey = "AIzaSyAPZkqgH44ts-FP6w_IFCO_Jogj9ExR0FM"; // API 키

        Video video = YouTubeUtils.fetchVideoDetails(videoId,apiKey);

        video = Video.builder()
                .videoId(videoId)
                .channelTitle()


        videoMapper.insertVideo(video);
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

    // 비디오 상세 정보 조회
    public Optional<Video> getVideoById(String videoId) {
        return videoMapper.findByVideoId(videoId);
    }

    // 비디오 검색
    public List<Video> searchVideos(String keyword) {
        return videoMapper.searchByKeyword(keyword);
    }
}
