package com.a502.backend.domain.allowance.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReceiptRequestDto {
    private String type;
    private String transactionUuid;
    private MultipartFile file;

    @Builder

    public ReceiptRequestDto(String type, String transactionUuid, MultipartFile file) {
        this.type = type;
        this.transactionUuid = transactionUuid;
        this.file = file;
    }
}
