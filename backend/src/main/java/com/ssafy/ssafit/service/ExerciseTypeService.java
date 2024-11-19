package com.ssafy.ssafit.service;

import com.ssafy.ssafit.domain.ExerciseMetData;
import com.ssafy.ssafit.dto.response.PagedResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ssafy.ssafit.utils.DTOMapper.toPagedResponseDTO;

@Service
@RequiredArgsConstructor
public class ExerciseTypeService {
    private final ExerciseMetService exerciseMetService;

    public PagedResponseDTO<ExerciseMetData> getPagedExerciseTypeResponse(int page, int size, String exerciseType){

        List<ExerciseMetData> exerciseMetDataList = exerciseMetService.getMetDataByPage(page, size, exerciseType);

        int totalItems = exerciseMetService.getTotalDataCount();

        return toPagedResponseDTO(page, size, totalItems, exerciseMetDataList);
    }
}
