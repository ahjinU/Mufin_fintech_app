package com.a502.backend.domain.allowance.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DetailDto {
    private String type;
    private String transactionUUID;

    @Builder
    public DetailDto(String type, String transactionUUID) {
        this.type = type;
        this.transactionUUID = transactionUUID;
    }
}
