package com.ssafy.ssafit.utils;

import com.ssafy.ssafit.domain.ExerciseLog;
import com.ssafy.ssafit.domain.ExerciseType;
import com.ssafy.ssafit.dto.response.ExerciseInfoResponseDTO;
import com.ssafy.ssafit.dto.response.MemberInfoResponseDTO;
import com.ssafy.ssafit.domain.Member;
import com.ssafy.ssafit.dto.response.SignUpResponseDTO;

import java.util.Date;

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
                .userId(member.getMemberId())
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
}