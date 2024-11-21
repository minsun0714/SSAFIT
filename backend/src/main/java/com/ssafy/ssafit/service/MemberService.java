package com.ssafy.ssafit.service;

import com.ssafy.ssafit.dao.MemberMapper;
import com.ssafy.ssafit.domain.JwtToken;
import com.ssafy.ssafit.domain.Role;
import com.ssafy.ssafit.dto.request.MemberInfoRequestDTO;
import com.ssafy.ssafit.dto.request.SignUpRequestDTO;
import com.ssafy.ssafit.dto.response.MemberInfoResponseDTO;
import com.ssafy.ssafit.domain.Member;
import com.ssafy.ssafit.dto.response.SignUpResponseDTO;
import com.ssafy.ssafit.exception.MemberNotAuthenticatedException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static com.ssafy.ssafit.utils.CookieUtils.setRefreshTokenCookie;
import static com.ssafy.ssafit.utils.DTOMapper.toMemberInfoDTO;
import static com.ssafy.ssafit.utils.DTOMapper.toSignUpResponseDTO;


@Service
@Transactional
public class MemberService {

    private final AuthService authService;
    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;

    public MemberService(AuthService authservice, MemberMapper memberMapper, PasswordEncoder passwordEncoder) {
        this.authService = authservice;
        this.memberMapper = memberMapper;
        this.passwordEncoder = passwordEncoder;
    }

    // 사용자 생성
    @Transactional
    public SignUpResponseDTO createMember(SignUpRequestDTO memberRequestDTO, HttpServletResponse response) {
        Member member = Member.builder()
                .memberId(memberRequestDTO.getMemberId())
                .password(passwordEncoder.encode(memberRequestDTO.getPassword())) // 보안상 해시 처리 필요
                .name(memberRequestDTO.getName())
                .nickname(memberRequestDTO.getNickname())
                .role(Role.ROLE_ADMIN)
                .build();

        System.out.println(member.getPassword());

        memberMapper.insertMember(member);

        JwtToken jwtToken = authService.authenticateAndGenerateToken(memberRequestDTO.getMemberId(), memberRequestDTO.getPassword());

        setRefreshTokenCookie(response, jwtToken.getRefreshToken());

        return toSignUpResponseDTO(member, jwtToken.getAccessToken());
    }

    // 사용자 조회
    public MemberInfoResponseDTO getMemberById() {
        String memberId = getAuthenticatedMemberId();
        System.out.println(memberMapper.findByMemberId(memberId));
        Member member = memberMapper.findByMemberId(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
        return toMemberInfoDTO(member);
    }

    // 사용자 정보 업데이트
    @Transactional
    public MemberInfoResponseDTO updateMember(MemberInfoRequestDTO memberRequestDTO, MultipartFile profileImg) {
        String memberId = getAuthenticatedMemberId();
        Member member = memberMapper.findByMemberId(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        // 비밀번호 변경 로직
        if (memberRequestDTO.getPassword() != null) {
            validatePasswordMatch(memberRequestDTO.getPassword(), memberRequestDTO.getPasswordConfirm());
            member.setPassword(passwordEncoder.encode(memberRequestDTO.getPassword())); // 보안상 해시 처리 필요
        }

        // 프로필 이미지 업로드 처리
        if (profileImg != null && !profileImg.isEmpty()) {
            String uploadedImagePath = uploadProfileImage(profileImg);
            member.setProfileImg(uploadedImagePath);
        }

        // 닉네임 및 기타 정보 업데이트
        member.setNickname(memberRequestDTO.getNickname());
        member.setWeight(memberRequestDTO.getWeight());

        // DB 업데이트
        memberMapper.updateMember(member);

        // 업데이트된 정보 반환
        return toMemberInfoDTO(member);
    }


    // 사용자 삭제
    @Transactional
    public void deleteMember() {
        String memberId = getAuthenticatedMemberId();
        if (!memberMapper.existsByMemberId(memberId)) {
            throw new IllegalArgumentException("Member not found");
        }
        memberMapper.deleteByMemberId(memberId);
    }

    // 비밀번호 일치 여부를 확인하는 메서드
    private void validatePasswordMatch(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new IllegalArgumentException("Passwords do not match");
        }
    }

    public String getAuthenticatedMemberId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof String)) {
            throw new MemberNotAuthenticatedException("Member not authenticated");
        }
        return (String) authentication.getPrincipal();
    }

    private String uploadProfileImage(MultipartFile profileImg) {
        try {
            // 파일 저장 경로 설정 (예: 로컬 디렉토리 또는 클라우드 스토리지)
            String uploadDir = "C:/path/to/upload/directory";
            String fileName = UUID.randomUUID() + "_" + profileImg.getOriginalFilename();

            // 파일 저장
            File destinationFile = new File(uploadDir + "/" + fileName);
            profileImg.transferTo(destinationFile);

            // 저장된 파일 경로 반환
            return "/uploads/" + fileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload profile image", e);
        }
    }
}
