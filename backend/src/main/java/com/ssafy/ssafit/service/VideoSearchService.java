package com.ssafy.ssafit.service;

import com.ssafy.ssafit.dao.VideoMapper;
import com.ssafy.ssafit.dao.VideoSearchMapper;
import com.ssafy.ssafit.domain.Video;
import com.ssafy.ssafit.domain.VideoSortType;
import com.ssafy.ssafit.dto.response.AutoCompleteVO;
import com.ssafy.ssafit.dto.response.PagedResponseDTO;
import com.ssafy.ssafit.dto.response.VideoCardVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ssafy.ssafit.utils.DTOMapper.toPagedResponseDTO;
import static com.ssafy.ssafit.utils.DTOMapper.toVideoCardVO;

@Service
@RequiredArgsConstructor
public class VideoSearchService {

    private final VideoSearchMapper videoSearchMapper;

    public PagedResponseDTO<VideoCardVO> getPaginatedAndSortedVideos(String keyword, int page, int size, String sort) {
        int offset = (page - 1) * size;

        // 데이터베이스에서 검색된 비디오 목록 조회
        List<Video> videos = videoSearchMapper.searchVideos(keyword, offset, size, sort);

        System.out.println(videos);

        // 총 검색 결과 수 조회
        int totalCount = videoSearchMapper.countVideos(keyword);

        // 페이지네이션 정보를 포함한 응답 객체 생성
        return toPagedResponseDTO(page, size, totalCount, toVideoCardVO(videos));
    }

    public List<AutoCompleteVO> getAutocompleteSuggestions(String keyword) {
        List<AutoCompleteVO> autoCompleteSuggestions = videoSearchMapper.getAutocompleteSuggestions(keyword); // 최대 5개 반환
        System.out.println("auto: " + autoCompleteSuggestions);
        return autoCompleteSuggestions;
    }
}