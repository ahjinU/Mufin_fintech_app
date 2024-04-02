package com.a502.backend.domain.allowance.request;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
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
