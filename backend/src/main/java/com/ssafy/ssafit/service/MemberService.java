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
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

import static com.ssafy.ssafit.utils.CookieUtils.setRefreshTokenCookie;
import static com.ssafy.ssafit.utils.DTOMapper.toMemberInfoDTO;
import static com.ssafy.ssafit.utils.DTOMapper.toSignUpResponseDTO;


@Service
@Transactional
public class MemberService {
    private final S3Client s3Client;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    @Value("${aws.s3.region}")
    private String region;

    private final AuthService authService;
    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;

    public MemberService(S3Client s3Client, AuthService authservice, MemberMapper memberMapper, PasswordEncoder passwordEncoder) {
        this.s3Client = s3Client;
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

    public String uploadProfileImage(MultipartFile profileImg) {
        try {
            // 고유한 파일 이름 생성
            String fileName = UUID.randomUUID() + "_" + profileImg.getOriginalFilename();

            // S3에 파일 업로드
            PutObjectResponse response = s3Client.putObject(
                    PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key("uploads/" + fileName)
                            .contentType(profileImg.getContentType())
                            .build(),
                    RequestBody.fromInputStream(profileImg.getInputStream(), profileImg.getSize())
            );

            // 업로드된 파일의 URL 반환
            return String.format("https://%s.s3.%s.amazonaws.com/uploads/%s", bucketName, region, fileName);
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload profile image", e);
        }
    }
}
