package com.ssafy.ssafit.controller;

import com.ssafy.ssafit.domain.ExerciseLog;
import com.ssafy.ssafit.dto.request.ExerciseInfoRequestDTO;
import com.ssafy.ssafit.dto.response.ExerciseCardDataDTO;
import com.ssafy.ssafit.dto.response.ExerciseInfoResponseDTO;
import com.ssafy.ssafit.service.ExerciseLogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/exercise-log")
@RequiredArgsConstructor
public class ExerciseLogController {

    private final ExerciseLogService exerciseLogService;

    // 운동 기록 생성
    @PostMapping
    public ResponseEntity<ExerciseInfoResponseDTO> createExerciseLog(@Valid @RequestBody ExerciseInfoRequestDTO exerciseInfoRequestDTO) {
        ExerciseInfoResponseDTO createdExerciseInfo = exerciseLogService.createExerciseLog(exerciseInfoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdExerciseInfo);
    }

    @GetMapping("/cards")
    public ResponseEntity<List<ExerciseCardDataDTO>> getCardDataList() {
        List<ExerciseCardDataDTO> exerciseCardDataDTOList = exerciseLogService.getCardDataList();
        return ResponseEntity.ok(exerciseCardDataDTOList);
    }

    // 특정 날짜 운동 기록 조회
    @GetMapping("/{exerciseDate}")
    public ResponseEntity<List<ExerciseInfoResponseDTO>> getExerciseLogsByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate exerciseDate) {
        List<ExerciseInfoResponseDTO> logs = exerciseLogService.getExerciseLogsByDate(Date.valueOf(exerciseDate));
        return ResponseEntity.ok(logs);
    }

    // 운동 기록 삭제
    @DeleteMapping("/{exerciseLogId}")
    public ResponseEntity<Void> deleteExerciseLog(@PathVariable Long exerciseLogId) {
        exerciseLogService.deleteExerciseLog(exerciseLogId);
        return ResponseEntity.noContent().build();
    }
}
