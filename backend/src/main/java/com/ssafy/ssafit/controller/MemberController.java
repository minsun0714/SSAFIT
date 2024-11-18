package com.ssafy.ssafit.controller;

import com.ssafy.ssafit.dto.request.MemberInfoRequestDTO;
import com.ssafy.ssafit.dto.request.SignUpRequestDTO;
import com.ssafy.ssafit.dto.response.MemberInfoResponseDTO;
import com.ssafy.ssafit.dto.response.SignUpResponseDTO;
import com.ssafy.ssafit.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 유저 생성 (회원가입)
    @PostMapping
    public ResponseEntity<SignUpResponseDTO> createUser(@Valid @RequestBody SignUpRequestDTO signUpFormDTO, HttpServletResponse response) {
        SignUpResponseDTO createdUser = memberService.createMember(signUpFormDTO, response);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    // 특정 유저 조회
    @GetMapping
    public ResponseEntity<MemberInfoResponseDTO> getUserById() {
        MemberInfoResponseDTO user = memberService.getMemberById();
        return ResponseEntity.ok(user);
    }

   // 유저 정보 업데이트
    @PutMapping
    public ResponseEntity<MemberInfoResponseDTO> updateUser(
            @Valid
            @RequestBody MemberInfoRequestDTO myInfoUpdateDTO) {
        MemberInfoResponseDTO updatedUser = memberService.updateMember(myInfoUpdateDTO);
        return ResponseEntity.ok(updatedUser);
    }

    // 유저 삭제
    @DeleteMapping
    public ResponseEntity<Void> deleteUser() {
        memberService.deleteMember();
        return ResponseEntity.noContent().build();
    }
}
