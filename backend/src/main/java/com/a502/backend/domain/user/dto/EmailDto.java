package com.a502.backend.domain.user.dto;

import jakarta.validation.constraints.Email;
import lombok.*;

@Getter
@NoArgsConstructor
public class EmailDto{
    private String email;
    @Builder
    public EmailDto(String email) {
        this.email = email;
    }


}