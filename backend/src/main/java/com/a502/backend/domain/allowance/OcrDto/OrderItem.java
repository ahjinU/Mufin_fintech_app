package com.a502.backend.domain.allowance.OcrDto;

import com.a502.backend.application.entity.ReceiptDetail;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {
    private String item;
    private int cnt;
    private int total;
    private int unitPrice;

    @Builder
    public OrderItem(String item, int cnt, int total, int unitPrice) {
        this.item = item;
        this.cnt = cnt;
        this.total = total;
        this.unitPrice = unitPrice;
    }

    public static OrderItem convertFromEntity(ReceiptDetail receiptDetail){

        OrderItem orderItem =  OrderItem.builder()
                .item(receiptDetail.getItem())
                .unitPrice(receiptDetail.getUnitPrice())
                .cnt(receiptDetail.getCnt())
                .total(receiptDetail.getTotal())
                .build();

        return orderItem;
    }

    // List<OrderItem> 반환하도록 수정
    public static List<OrderItem> getOrderList(List<ReceiptDetail> receiptDetails) {

        List<OrderItem> items = new ArrayList<>();

        for(ReceiptDetail detail: receiptDetails){
            items.add(convertFromEntity(detail));
        }

        return items;
    }
}

