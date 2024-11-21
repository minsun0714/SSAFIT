package com.ssafy.ssafit.service;

import com.ssafy.ssafit.dao.ExerciseLogMapper;
import com.ssafy.ssafit.dao.MemberMapper;
import com.ssafy.ssafit.domain.ExerciseLog;
import com.ssafy.ssafit.domain.ExerciseMetData;
import com.ssafy.ssafit.domain.Member;
import com.ssafy.ssafit.dto.request.ExerciseInfoRequestDTO;
import com.ssafy.ssafit.dto.response.ExerciseCardDataDTO;
import com.ssafy.ssafit.dto.response.ExerciseInfoResponseDTO;
import com.ssafy.ssafit.dto.response.ExerciseLogVO;
import com.ssafy.ssafit.utils.DTOMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.ssafy.ssafit.utils.DTOMapper.toExerciseCardDataDTO;
import static com.ssafy.ssafit.utils.DTOMapper.toExerciseInfoDTO;

@Service
@RequiredArgsConstructor
public class ExerciseLogService {

    private final MemberMapper memberMapper;
    private final ExerciseLogMapper exerciseLogMapper;
    private final ExerciseMetService exerciseMetService;

    // 운동 기록 생성
    @Transactional
    public ExerciseLogVO createExerciseLog(ExerciseInfoRequestDTO exerciseInfoRequestDTO) {
        String memberId = getAuthenticatedMemberId();

        // MET 데이터를 가져옴
        ExerciseMetData exerciseMetData = exerciseMetService.getMetData(exerciseInfoRequestDTO.getExerciseType());

        Member member = memberMapper.findByMemberId(memberId).orElseThrow();
        // 체중은 사용자로부터 입력받는다고 가정 (필요시 RequestDTO에 weight 필드 추가)
        int weight = Optional.of(member.getWeight()).orElse(65);

        // 초 단위 운동 시간을 시간 단위로 변환
        double exerciseTimeInHours = exerciseInfoRequestDTO.getExerciseTime() / 3600.0;

        // 칼로리 소모량 및 지방 소모량 계산
        double caloriesBurned = calculateCalories(exerciseMetData.getMet(), exerciseTimeInHours, weight);
        double fatBurned = calculateFatBurned(caloriesBurned);

        // 운동 기록 객체 생성
        ExerciseLog exerciseLog = ExerciseLog.builder()
                .memberId(memberId)
                .exerciseDate(exerciseInfoRequestDTO.getExerciseDate())
                .exerciseType(exerciseInfoRequestDTO.getExerciseType())
                .exerciseTime(exerciseInfoRequestDTO.getExerciseTime())
                .caloriesBurned(caloriesBurned)
                .fatBurned(fatBurned)
                .build();

        exerciseLogMapper.insertExerciseLog(exerciseLog);
        ExerciseLog savedExerciseLog = exerciseLogMapper.selectOneExerciseLog(exerciseLog.getExerciseLogId())
                .orElseThrow(() -> new IllegalArgumentException("Failed to retrieve the saved exercise log"));

        return toExerciseInfoDTO(savedExerciseLog);
    }

    // 카드 데이터 기록 조회
    public List<ExerciseCardDataDTO> getCardDataList(){
        String memberId = getAuthenticatedMemberId();

        Map<String, Double> thisWeekExerciseData = exerciseLogMapper.selectThisWeekExerciseData(memberId);
        Map<String, Double> lastWeekExerciseData = exerciseLogMapper.selectLastWeekExerciseData(memberId);

        return toExerciseCardDataDTO(thisWeekExerciseData, lastWeekExerciseData);
    }

    // 특정 날짜의 운동 기록 조회
    public ExerciseInfoResponseDTO getExerciseLogsByDate(Date exerciseDate) {
        String memberId = getAuthenticatedMemberId();

        List<ExerciseLogVO> exerciseLogs = exerciseLogMapper.selectExerciseLogsByDate(memberId, exerciseDate);

        Long dailyTotalExerciseTime = exerciseLogMapper.selectTotalExerciseTimeByDate(memberId, exerciseDate);

        return ExerciseInfoResponseDTO.builder()
                .exerciseLogVO(exerciseLogs)
                .dailyTotalExerciseTime(dailyTotalExerciseTime)
                .build();
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

    // 칼로리 소모량 계산
    private double calculateCalories(double met, double exerciseTimeInHours, double weight) {
        return met * weight * exerciseTimeInHours;
    }

    // 지방 소모량 계산
    private double calculateFatBurned(double caloriesBurned) {
        return (caloriesBurned * 0.7) / 9.0;
    }
}
