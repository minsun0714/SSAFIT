package com.ssafy.ssafit.service;

import com.ssafy.ssafit.dao.MemberMapper;
import com.ssafy.ssafit.domain.JwtToken;
import com.ssafy.ssafit.domain.Role;
import com.ssafy.ssafit.dto.request.SignUpRequestDTO;
import com.ssafy.ssafit.dto.response.MemberInfoDTO;
import com.ssafy.ssafit.domain.Member;
import com.ssafy.ssafit.dto.response.SignUpResponseDTO;
import com.ssafy.ssafit.exception.MemberNotAuthenticatedException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        System.out.println(memberRequestDTO.getPasswordConfirm());
        Member member = Member.builder()
                .memberId(memberRequestDTO.getMemberId())
                .password(passwordEncoder.encode(memberRequestDTO.getPassword())) // 보안상 해시 처리 필요
                .name(memberRequestDTO.getName())
                .nickname(memberRequestDTO.getNickname())
                .role(Role.ROLE_ADMIN)
                .build();

        memberMapper.insertMember(member);
        System.out.println("insert후");

        JwtToken jwtToken = authService.authenticateAndGenerateToken(memberRequestDTO.getMemberId(), memberRequestDTO.getPassword());
        System.out.println(jwtToken);
        System.out.println("insert한 직후 확인" + memberMapper.findByMemberId(member.getMemberId()));

        setRefreshTokenCookie(response, jwtToken.getRefreshToken());

        return toSignUpResponseDTO(member, jwtToken.getAccessToken());
    }

    // 사용자 조회
    public MemberInfoDTO getMemberById() {
        String memberId = getAuthenticatedMemberId();
        System.out.println(memberId);
        Member member = memberMapper.findByMemberId(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
        return toMemberInfoDTO(member);
    }

    // 사용자 정보 업데이트
//    @Transactional
//    public MemberInfoDTO updateMember(MyInfoUpdateDTO memberRequestDTO) {
//        String memberId = getAuthenticatedMemberId();
//        Member member = memberMapper.findByMemberId(memberId)
//                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
//
//        if (memberRequestDTO.getPassword() != null) {
//            validatePasswordMatch(memberRequestDTO.getPassword(), memberRequestDTO.getConfirmPassword());
//            member.setPassword(passwordEncoder.encode(memberRequestDTO.getPassword())); // 보안상 해시 처리 필요
//        }
//
//        member.setProfileImg(memberRequestDTO.getProfileImg());
//        member.setNickname(memberRequestDTO.getNickname());
//
//        memberMapper.updateMember(member);
//
//        return toMemberInfoDTO(member);
//    }

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
}
