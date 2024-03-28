package com.a502.backend.domain.allowance.response;

import com.a502.backend.domain.allowance.OcrDto.OrderItem;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class TransactionDetailDto extends TransactionDto{
    private List<OrderItem> receipts;
    private String memo;

    @Builder
    public TransactionDetailDto(String counterpartyName, String code, String transactionUuid, int amount, LocalDateTime date, List<OrderItem> receipts, String memo) {
        super(counterpartyName, code, transactionUuid, amount, date);
        this.receipts = receipts;
        this.memo = memo;
    }
}
