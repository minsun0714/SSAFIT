package com.ssafy.ssafit.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberInfoRequestDTO {
    private String nickname;
    private String password;
    private String passwordConfirm;
    private String profileImg;
    private boolean isFollowerListOpen;
}