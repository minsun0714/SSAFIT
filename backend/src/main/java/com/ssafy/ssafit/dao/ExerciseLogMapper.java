package com.ssafy.ssafit.dao;

import com.ssafy.ssafit.domain.ExerciseLog;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ExerciseLogMapper {
    void insertExerciseLog(ExerciseLog log);

    Map<String, Double> selectThisWeekExerciseData(@Param("memberId") String memberId);

    Map<String, Double> selectLastWeekExerciseData(@Param("memberId") String memberId);

    List<ExerciseLog> selectExerciseLogsByDate(@Param("memberId") String memberId, @Param("exerciseDate") Date exerciseDate);

    Optional<ExerciseLog> selectOneExerciseLog(@Param("exerciseLogId") Long exerciseLogId);

    void deleteExerciseLog(@Param("exerciseLogId") Long exerciseLogId);
}
