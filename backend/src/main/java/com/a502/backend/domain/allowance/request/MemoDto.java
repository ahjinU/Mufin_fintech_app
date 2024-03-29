package com.a502.backend.domain.allowance.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemoDto {
    private String type;
    private String transactionUuid;
    private String memo;

    @Builder
    public MemoDto(String type, String transactionUuid, String memo) {
        this.type = type;
        this.transactionUuid = transactionUuid;
        this.memo = memo;
    }
}
