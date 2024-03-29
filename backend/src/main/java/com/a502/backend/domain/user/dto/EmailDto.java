package com.a502.backend.domain.user.dto;

import jakarta.validation.constraints.Email;
import lombok.*;

@Getter
@NoArgsConstructor
public class EmailDto{
    @Email(message = "올바른 이메일 주소를 입력해주세요.")
    private String email;
    @Builder
    public EmailDto(String email) {
        this.email = email;
    }


}