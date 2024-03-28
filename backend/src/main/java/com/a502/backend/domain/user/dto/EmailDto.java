package com.a502.backend.domain.user.dto;

import lombok.*;

@Getter
@NoArgsConstructor
public class EmailDto{
    private String email;
    private String temporaryUserUuid;

    @Builder

    public EmailDto(String email, String temporaryUserUuid) {
        this.email = email;
        this.temporaryUserUuid = temporaryUserUuid;
    }
}