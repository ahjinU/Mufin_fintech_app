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

    // 정적 메서드로 수정하고 반환 타입을 OrderItem으로 변경
    public static OrderItem convertFromEntity(ReceiptDetail receiptDetail){

        System.out.println("convertFromEntity!");
        OrderItem orderItem =  OrderItem.builder()
                .item(receiptDetail.getItem())
                .unitPrice(receiptDetail.getUnitPrice())
                .cnt(receiptDetail.getCnt())
                .total(receiptDetail.getTotal())
                .build();

        System.out.println("변환 !");
        System.out.println(orderItem.toString());

        return orderItem;
    }

    // List<OrderItem> 반환하도록 수정
    public static List<OrderItem> getOrderList(List<ReceiptDetail> receiptDetails) {

        System.out.println("getOrderList!: "+receiptDetails.size());

        List<OrderItem> items = new ArrayList<>();

        for(ReceiptDetail detail: receiptDetails){
            items.add(convertFromEntity(detail)); // 여기에서 convertFromEntity 호출
        }

        return items; // 리스트 반환
    }
}

