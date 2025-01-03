package com.ssafy.ssafit.dto.response;

import com.ssafy.ssafit.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberInfoResponseDTO {
    private String memberId;
    private String name;
    private String nickname;
    private String profileImg;
    private Role role;
    private int weight;
}