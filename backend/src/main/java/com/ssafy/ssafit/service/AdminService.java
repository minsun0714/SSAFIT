package com.ssafy.ssafit.service;

import com.ssafy.ssafit.dao.AdminMapper;
import com.ssafy.ssafit.domain.Video;
import com.ssafy.ssafit.domain.VideoStatus;
import com.ssafy.ssafit.dto.response.PagedResponseDTO;
import com.ssafy.ssafit.dto.response.VideoCardVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ssafy.ssafit.utils.DTOMapper.toPagedResponseDTO;
import static com.ssafy.ssafit.utils.DTOMapper.toVideoCardVO;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminMapper adminMapper;

    public PagedResponseDTO<VideoCardVO> getPendingVideos(int page, int size) {
        int offset = page * size;
        List<Video> videos = adminMapper.findPendingVideos(offset, size);
        int totalCount = adminMapper.countPendingVideos();
        return toPagedResponseDTO(page, size, totalCount, toVideoCardVO(videos));
    }

    public void updateVideoStatus(String videoId, VideoStatus status) {
        adminMapper.updateVideoStatus(videoId, status);
    }
}
