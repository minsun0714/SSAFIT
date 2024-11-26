package com.ssafy.ssafit.dao;

import com.ssafy.ssafit.domain.ExerciseLog;
import com.ssafy.ssafit.dto.response.ExerciseGrassVO;
import com.ssafy.ssafit.dto.response.ExerciseLogVO;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ExerciseLogMapper {
    void insertExerciseLog(ExerciseLog log);

    Map<String, Double> selectThisWeekExerciseData(@Param("memberId") String memberId);

    Map<String, Double> selectLastWeekExerciseData(@Param("memberId") String memberId);

    List<ExerciseLogVO> selectExerciseLogsByDate(@Param("memberId") String memberId, @Param("exerciseDate") Date exerciseDate);

    Optional<ExerciseLog> selectOneExerciseLog(@Param("exerciseLogId") Long exerciseLogId);

    int selectTotalExerciseTimeByDate(@Param("memberId") String memberId, @Param("exerciseDate") Date exerciseDate);

    List<Map<String, Object>> selectTotalExerciseTimeByDateRange(
            @Param("memberId") String memberId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    void deleteExerciseLog(@Param("exerciseLogId") Long exerciseLogId);
}
