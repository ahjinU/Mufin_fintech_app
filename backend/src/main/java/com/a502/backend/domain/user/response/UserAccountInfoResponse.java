package com.a502.backend.domain.user.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserAccountInfoResponse {
    String accountUuid;
    int balance;
    int savings;
    int monthAmounts;
}
