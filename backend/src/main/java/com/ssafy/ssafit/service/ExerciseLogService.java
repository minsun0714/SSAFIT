package com.ssafy.ssafit.service;

import com.ssafy.ssafit.dao.ExerciseLogMapper;
import com.ssafy.ssafit.domain.ExerciseLog;
import com.ssafy.ssafit.dto.request.ExerciseInfoRequestDTO;
import com.ssafy.ssafit.dto.response.ExerciseInfoResponseDTO;
import com.ssafy.ssafit.utils.DTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.ssafy.ssafit.utils.DTOMapper.toExerciseInfoDTO;

@Service
@RequiredArgsConstructor
public class ExerciseLogService {

    private final ExerciseLogMapper exerciseLogMapper;

    // 운동 기록 생성
    @Transactional
    public ExerciseInfoResponseDTO createExerciseLog(ExerciseInfoRequestDTO exerciseInfoRequestDTO) {
        String memberId = getAuthenticatedMemberId();

        ExerciseLog exerciseLog = ExerciseLog.builder()
                .memberId(memberId)
                .exerciseDate(exerciseInfoRequestDTO.getExerciseDate())
                .exerciseType(exerciseInfoRequestDTO.getExerciseType())
                .exerciseTime(exerciseInfoRequestDTO.getExerciseTime())
                .caloriesBurned(100L)
                .fatBurned(900L)
                .build();

        exerciseLogMapper.insertExerciseLog(exerciseLog);
        ExerciseLog savedExerciseLog = exerciseLogMapper.selectOneExerciseLog(exerciseLog.getExerciseLogId())
                .orElseThrow(() -> new IllegalArgumentException("Failed to retrieve the saved exercise log"));

        return toExerciseInfoDTO(savedExerciseLog);
    }

    // 특정 날짜의 운동 기록 조회
    public List<ExerciseInfoResponseDTO> getExerciseLogsByDate(Date exerciseDate) {
        String memberId = getAuthenticatedMemberId();

        List<ExerciseLog> exerciseLogs = exerciseLogMapper.selectExerciseLogs(memberId, exerciseDate);

        return exerciseLogs.stream()
                .map(DTOMapper::toExerciseInfoDTO)
                .collect(Collectors.toList());
    }

    // 운동 기록 삭제
    @Transactional
    public void deleteExerciseLog(Long exerciseLogId) {
        String memberId = getAuthenticatedMemberId();
        ExerciseLog log = exerciseLogMapper.selectOneExerciseLog(exerciseLogId)
                .orElseThrow(() -> new IllegalArgumentException("ExerciseLog not found"));

        if (!log.getMemberId().equals(memberId)) {
            throw new IllegalArgumentException("Unauthorized to delete this log");
        }

        exerciseLogMapper.deleteExerciseLog(exerciseLogId);
    }

    // 인증된 회원 ID 가져오기
    private String getAuthenticatedMemberId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof String)) {
            throw new IllegalArgumentException("Member not authenticated");
        }
        return (String) authentication.getPrincipal();
    }
}
