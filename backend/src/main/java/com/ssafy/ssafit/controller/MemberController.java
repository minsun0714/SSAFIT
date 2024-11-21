package com.ssafy.ssafit.controller;

import com.ssafy.ssafit.dto.request.MemberInfoRequestDTO;
import com.ssafy.ssafit.dto.request.SignUpRequestDTO;
import com.ssafy.ssafit.dto.response.MemberInfoResponseDTO;
import com.ssafy.ssafit.dto.response.SignUpResponseDTO;
import com.ssafy.ssafit.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/members")
@AllArgsConstructor
public class MemberController {

    private final MemberService memberService;

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
   @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
   public ResponseEntity<MemberInfoResponseDTO> updateUser(
           @RequestPart(value = "profileImg", required = false) MultipartFile profileImg,
           @RequestPart("memberInfo") MemberInfoRequestDTO myInfoUpdateDTO) {
       System.out.println("Profile Image: " + (profileImg != null ? profileImg.getOriginalFilename() : "No file uploaded"));
       System.out.println("Member Info: " + myInfoUpdateDTO);

       // Service 로직 호출
       MemberInfoResponseDTO updatedUser = memberService.updateMember(myInfoUpdateDTO, profileImg);
       return ResponseEntity.ok(updatedUser);
   }


    // 유저 삭제
    @DeleteMapping
    public ResponseEntity<Void> deleteUser() {
        memberService.deleteMember();
        return ResponseEntity.noContent().build();
    }
}
