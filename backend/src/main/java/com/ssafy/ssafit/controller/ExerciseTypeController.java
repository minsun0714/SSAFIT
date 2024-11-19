package com.ssafy.ssafit.controller;

import com.ssafy.ssafit.domain.ExerciseMetData;
import com.ssafy.ssafit.dto.response.PagedResponseDTO;
import com.ssafy.ssafit.service.ExerciseTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.ssafy.ssafit.utils.DTOMapper.toPagedResponseDTO;

@RestController
@RequestMapping("/api/exercise-type")
@RequiredArgsConstructor
public class ExerciseTypeController {
    private final ExerciseTypeService exerciseTypeService;
    @GetMapping
    public ResponseEntity<PagedResponseDTO<ExerciseMetData>> getPagedExerciseTypes(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String exerciseType
    )
    {

        PagedResponseDTO<ExerciseMetData> pagedExerciseTypeResponse = exerciseTypeService.getPagedExerciseTypeResponse(page, size, exerciseType);

        return ResponseEntity.ok(pagedExerciseTypeResponse);
    }
}
