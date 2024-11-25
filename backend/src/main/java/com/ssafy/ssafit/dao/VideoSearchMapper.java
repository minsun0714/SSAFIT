package com.ssafy.ssafit.dao;

import com.ssafy.ssafit.domain.Video;
import com.ssafy.ssafit.domain.VideoSortType;
import com.ssafy.ssafit.dto.response.AutoCompleteVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoSearchMapper {

    List<Video> searchVideos(@Param("keyword") String keyword , @Param("offset") int offset, @Param("size") int size, @Param("sortType") String sortType);

    int countVideos(String keyword);

    List<AutoCompleteVO> getAutocompleteSuggestions(@Param("keyword") String keyword);
}