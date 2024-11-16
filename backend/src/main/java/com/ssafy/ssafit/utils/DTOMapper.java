package com.ssafy.ssafit.utils;

import com.ssafy.ssafit.dto.response.MemberInfoDTO;
import com.ssafy.ssafit.domain.Member;
import com.ssafy.ssafit.dto.response.SignUpResponseDTO;

public class DTOMapper {

    private DTOMapper() {
    } // 인스턴스화 방지

    // User -> UserInfoDTO 변환
    public static MemberInfoDTO toMemberInfoDTO(Member member) {
        return MemberInfoDTO.builder()
                .memberId(member.getMemberId())
                .name(member.getName())
                .nickname(member.getNickname())
                .profileImg(member.getProfileImg())
                .role(member.getRole())
                .build();
    }

    // Player와 accessToken을 이용해 SignUpResponseDTO로 변환
    public static SignUpResponseDTO toSignUpResponseDTO(Member member, String accessToken) {
        return SignUpResponseDTO.builder()
                .name(member.getName())
                .userId(member.getMemberId())
                .nickname(member.getNickname())
                .profileImg(member.getProfileImg())
                .accessToken(accessToken)
                .build();
    }
}