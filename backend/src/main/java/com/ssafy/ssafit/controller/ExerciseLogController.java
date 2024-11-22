package com.ssafy.ssafit.controller;

import com.ssafy.ssafit.domain.ExerciseMetData;
import com.ssafy.ssafit.dto.request.ExerciseInfoRequestDTO;
import com.ssafy.ssafit.dto.response.*;
import com.ssafy.ssafit.service.ExerciseLogService;
import com.ssafy.ssafit.service.ExerciseMetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static com.ssafy.ssafit.utils.DTOMapper.toPagedResponseDTO;

@RestController
@RequestMapping("/api/exercise-log")
@RequiredArgsConstructor
public class ExerciseLogController {

    private final ExerciseLogService exerciseLogService;
    private final ExerciseMetService exerciseMetService;

    // 운동 기록 생성
    @PostMapping
    public ResponseEntity<ExerciseLogVO> createExerciseLog(@Valid @RequestBody ExerciseInfoRequestDTO exerciseInfoRequestDTO) {
        ExerciseLogVO createdExerciseInfo = exerciseLogService.createExerciseLog(exerciseInfoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdExerciseInfo);
    }

    @GetMapping("/cards")
    public ResponseEntity<List<ExerciseCardDataDTO>> getCardDataList() {
        List<ExerciseCardDataDTO> exerciseCardDataDTOList = exerciseLogService.getCardDataList();
        return ResponseEntity.ok(exerciseCardDataDTOList);
    }

    // 특정 날짜 운동 기록 조회
    @GetMapping("/{exerciseDate}")
    public ResponseEntity<ExerciseInfoResponseDTO> getExerciseLogsByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate exerciseDate) {
        ExerciseInfoResponseDTO logs = exerciseLogService.getExerciseLogsByDate(Date.valueOf(exerciseDate));
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/grass")
    public ResponseEntity<List<ExerciseGrassVO>> getYearlyExerciseGrass() {
        List<ExerciseGrassVO> exerciseLogVO = exerciseLogService.getYearlyExerciseGrass();
        return ResponseEntity.ok(exerciseLogVO);
    }

    // 운동 기록 삭제
    @DeleteMapping("/{exerciseLogId}")
    public ResponseEntity<Void> deleteExerciseLog(@PathVariable Long exerciseLogId) {
        exerciseLogService.deleteExerciseLog(exerciseLogId);
        return ResponseEntity.noContent().build();
    }
}
