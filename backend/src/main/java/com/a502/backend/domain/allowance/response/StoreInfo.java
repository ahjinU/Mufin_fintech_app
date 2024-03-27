package com.a502.backend.domain.allowance.response;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreInfo {
    private String name;
    private String address;
    private String tel;

    @Builder
    public StoreInfo(String name, String address, String tel) {
        this.name = name;
        this.address = address;
        this.tel = tel;
    }
}
