package com.a502.backend.domain.account.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AccountValidPasswordResponse {
    int incorrectCnt;
}
