package com.ssafy.ssafit.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpRequestDTO {
    @NotBlank(message = "User ID is required")
    @Size(min = 4, max = 20, message = "User ID must be between 4 and 20 characters")
    private String memberId;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Nickname is required")
    private String nickname;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,20}$",
            message = "Password must contain both letters and numbers")
    private String password;

    @NotBlank(message = "Password Confirm is required")
    @Size(min = 8, max = 20, message = "Password Confirm must be between 8 and 20 characters")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,20}$",
            message = "Password Confirm must contain both letters and numbers")
    private String passwordConfirm;
}