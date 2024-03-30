package com.a502.backend.domain.allowance.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DayDto {
    private String date;
    private String childUuid;

    @Builder
    public DayDto(String date, String childUuid) {
        this.date = date;
        this.childUuid = childUuid;
    }
}
