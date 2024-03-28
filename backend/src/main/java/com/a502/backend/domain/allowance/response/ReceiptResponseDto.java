package com.a502.backend.domain.allowance.response;

import com.a502.backend.application.entity.Receipt;
import com.a502.backend.domain.allowance.OcrDto.OrderItem;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReceiptResponseDto {
    private String storeName;
    private String storeAddress;
    private int totalPrice;
    private List<OrderItem> orderItems;

    @Builder
    public ReceiptResponseDto(String storeName, String storeAddress, int totalPrice, List<OrderItem> orderItems) {
        this.storeName = storeName;
        this.storeAddress = storeAddress;
        this.totalPrice = totalPrice;
        this.orderItems = orderItems;
    }

    public static ReceiptResponseDto convertFromEntity(Receipt receipt) {
        System.out.println("convertFromEntity!");

        System.out.println(receipt.getReceiptDetails());
        System.out.println("===========");

        List<OrderItem> convertedOrderItems = OrderItem.getOrderList(receipt.getReceiptDetails());

        System.out.println("orderItems 변환");

        return ReceiptResponseDto.builder()
                .storeName(receipt.getStoreName())
                .storeAddress(receipt.getStoreAddress())
                .totalPrice(receipt.getPrice())
                .orderItems(convertedOrderItems)
                .build();
    }


}
