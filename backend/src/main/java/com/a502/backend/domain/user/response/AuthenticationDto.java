package com.a502.backend.domain.user.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AuthenticationDto {
    private String temporaroyUserUuid;

    @Builder
    public AuthenticationDto(String temporaroyUserUuid) {
        this.temporaroyUserUuid = temporaroyUserUuid;
    }
}