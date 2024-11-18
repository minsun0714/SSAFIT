package com.ssafy.ssafit.utils;

import com.ssafy.ssafit.domain.*;
import com.ssafy.ssafit.dto.response.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class DTOMapper {

    private DTOMapper() {
    } // 인스턴스화 방지

    // Member -> MemberInfoDTO 변환
    public static MemberInfoResponseDTO toMemberInfoDTO(Member member) {
        return MemberInfoResponseDTO.builder()
                .memberId(member.getMemberId())
                .name(member.getName())
                .nickname(member.getNickname())
                .profileImg(member.getProfileImg())
                .role(member.getRole())
                .build();
    }

    // member와 accessToken을 이용해 SignUpResponseDTO로 변환
    public static SignUpResponseDTO toSignUpResponseDTO(Member member, String accessToken) {
        return SignUpResponseDTO.builder()
                .name(member.getName())
                .memberId(member.getMemberId())
                .nickname(member.getNickname())
                .profileImg(member.getProfileImg())
                .accessToken(accessToken)
                .build();
    }

    public static ExerciseInfoResponseDTO toExerciseInfoDTO(ExerciseLog exerciseLog) {
        return ExerciseInfoResponseDTO.builder()
                .exerciseLogId(exerciseLog.getExerciseLogId())
                .exerciseDate(exerciseLog.getExerciseDate())
                .exerciseType(exerciseLog.getExerciseType())
                .exerciseTime(exerciseLog.getExerciseTime())
                .caloriesBurned(exerciseLog.getCaloriesBurned())
                .fatBurned(exerciseLog.getFatBurned())
                .build();
    }

    public static List<ExerciseCardDataDTO> toExerciseCardDataDTO(Map<String, Double> thisWeekExerciseData, Map<String, Double> lastWeekExerciseData) {
        List<ExerciseCardDataDTO> cardDataList = new ArrayList<>();

        // 총 운동 시간 카드 생성
        double thisWeekExerciseTime = thisWeekExerciseData.get("totalExerciseTime");
        double lastWeekExerciseTime = lastWeekExerciseData.get("totalExerciseTime");

        ExerciseCardDataDTO exerciseTimeCard = ExerciseCardDataDTO.builder()
                .title("이번주 운동시간")
                .cardIconUrl("exercise_time_icon.png")
                .cardType(CardType.MINUTE)
                .currentValue(thisWeekExerciseTime)
                .lastValue(lastWeekExerciseTime)
                .isUp(thisWeekExerciseTime > lastWeekExerciseTime)
                .build();

        cardDataList.add(exerciseTimeCard);

        // 총 소모 칼로리 카드 생성
        double thisWeekCaloriesBurned = thisWeekExerciseData.get("totalCaloriesBurned");
        double lastWeekCaloriesBurned = lastWeekExerciseData.get("totalCaloriesBurned");

        ExerciseCardDataDTO caloriesBurnedCard = ExerciseCardDataDTO.builder()
                .title("이번주 소모 칼로리")
                .cardIconUrl("calories_burned_icon.png")
                .cardType(CardType.KCAL)
                .currentValue(thisWeekCaloriesBurned)
                .lastValue(lastWeekCaloriesBurned)
                .isUp(thisWeekCaloriesBurned > lastWeekCaloriesBurned)
                .build();

        cardDataList.add(caloriesBurnedCard);

        // 총 소모 지방 카드 생성
        double thisWeekFatBurned = thisWeekExerciseData.get("totalFatBurned");
        double lastWeekFatBurned = lastWeekExerciseData.get("totalFatBurned");

        ExerciseCardDataDTO fatBurnedCard = ExerciseCardDataDTO.builder()
                .title("이번주 없애버린 지방")
                .cardIconUrl("fat_burned_icon.png")
                .cardType(CardType.KG)
                .currentValue(thisWeekFatBurned)
                .lastValue(lastWeekFatBurned)
                .isUp(thisWeekFatBurned > lastWeekFatBurned)
                .build();

        cardDataList.add(fatBurnedCard);

        return cardDataList;
    }

    public static <T> PagedResponseDTO<T> toPagedResponseDTO(int page, int size, int totalDataCount, List<T> data) {
        int totalPages = (int) Math.ceil((double) totalDataCount / size);

        return new PagedResponseDTO<>(
                page,
                size,
                totalPages,
                totalDataCount,
                data
        );
    }


}