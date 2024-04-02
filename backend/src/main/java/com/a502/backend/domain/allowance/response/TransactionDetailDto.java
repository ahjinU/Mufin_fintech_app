package com.a502.backend.domain.allowance.response;

import com.a502.backend.application.entity.AccountDetail;
import com.a502.backend.application.entity.CashDetail;
import com.a502.backend.application.entity.Receipt;
import com.a502.backend.application.entity.ReceiptDetail;
import com.a502.backend.domain.allowance.OcrDto.OrderItem;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class TransactionDetailDto {
    protected String counterpartyName;
    protected String code;
    protected String transactionUuid;
    protected int amount;
    protected LocalDateTime date;
    private List<OrderItem> receipts;
    private String memo;

    @Builder
    public TransactionDetailDto(String counterpartyName, String code, String transactionUuid, int amount, LocalDateTime date, List<OrderItem> receipts, String memo) {
        this.counterpartyName = counterpartyName;
        this.code = code;
        this.transactionUuid = transactionUuid;
        this.amount = amount;
        this.date = date;
        this.receipts = receipts;
        this.memo = memo;
    }

    public static List<TransactionDetailDto> convertFromAccountDetails(List<AccountDetail> accountDetails) {
        List<TransactionDetailDto> result = new ArrayList<>();

        for (AccountDetail detail : accountDetails) {
            TransactionDetailDto transactionDetailDto = convertFromAccountDetail(detail);
            result.add(transactionDetailDto);
        }

        return result;
    }

    public static TransactionDetailDto convertFromAccountDetail(AccountDetail detail) {

        String memo = (detail.getMemo() != null && detail.getMemo().getContent() != null) ? detail.getMemo().getContent() : "";

        return TransactionDetailDto.builder()
                .counterpartyName(detail.getCounterpartyName())
                .code("계좌")
                .transactionUuid(detail.getAccountDetailUuid().toString())
                .amount(detail.getAmount())
                .date(detail.getCreatedAt())
                .memo(memo)
                .receipts(getReceipt(detail.getReceipt()))
                .build();
    }

    public static List<TransactionDetailDto> convertFromCashDetails(List<CashDetail> cashDetails) {
        List<TransactionDetailDto> result = new ArrayList<>();
        for (CashDetail detail : cashDetails) {

            TransactionDetailDto transactionDetailDto =convertFromCashDetail(detail);

            result.add(transactionDetailDto);
        }
        return result;
    }

    public static TransactionDetailDto convertFromCashDetail(CashDetail detail) {

        String memo = (detail.getMemo() != null && detail.getMemo().getContent() != null) ? detail.getMemo().getContent() : "";

        return TransactionDetailDto.builder()
                .counterpartyName(detail.getUsageName())
                .code("현금")
                .transactionUuid(detail.getCashDetailUuid().toString())
                .amount(detail.getAmount())
                .date(detail.getCreatedAt())
                .memo(memo)
                .receipts(getReceipt(detail.getReceipt()))
                .build();


    }

    private static List<OrderItem> getReceipt(Receipt receipt) {
        if (receipt == null)
            return null;
        List<OrderItem> items = new ArrayList<>();
        List<ReceiptDetail> receiptDetails = receipt.getReceiptDetails();

        for (ReceiptDetail item : receiptDetails) {
            OrderItem orderItem = OrderItem.builder()
                    .total(item.getTotal())
                    .item(item.getItem())
                    .cnt(item.getCnt())
                    .unitPrice(item.getUnitPrice())
                    .build();

            items.add(orderItem);
        }

        return items;
    }
}
