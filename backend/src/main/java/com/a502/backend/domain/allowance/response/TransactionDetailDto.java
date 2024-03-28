package com.a502.backend.domain.allowance.response;

import com.a502.backend.domain.allowance.OcrDto.OrderItem;
import lombok.Builder;

import java.util.List;

public class TransactionDetailDto extends TransactionDto{
    private List<OrderItem> receipts;
    private String memo;

    @Builder
    public TransactionDetailDto(String counterpartyName, String code, String transactionUUID, int amount, String memo, List<OrderItem> receipts, String memo1) {
        super(counterpartyName, code, transactionUUID, amount, memo);
        this.receipts = receipts;
        this.memo = memo1;
    }
}
