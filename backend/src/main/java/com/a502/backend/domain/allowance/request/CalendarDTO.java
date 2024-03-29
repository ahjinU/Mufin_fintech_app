package com.a502.backend.domain.allowance.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CalendarDTO{
    private String startDate;
    private String endDate;
    private String childUuid;

    @Builder
    public CalendarDTO(String startDate, String endDate, String childUuid) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.childUuid = childUuid;
    }
}
