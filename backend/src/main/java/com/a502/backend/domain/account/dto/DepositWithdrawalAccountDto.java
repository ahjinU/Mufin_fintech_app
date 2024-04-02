package com.a502.backend.domain.account.dto;

import com.a502.backend.application.entity.Account;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepositWithdrawalAccountDto {
    private String accountNumber; // 계좌번호
    private int balance; // 잔고
    private int interestAmount; // 이자율

    // 계좌 엔터티로부터 DTO 생성을 위한 정적 메소드
    public static DepositWithdrawalAccountDto fromEntity(Account account) {
        return new DepositWithdrawalAccountDto(
                account.getAccountNumber(),
                account.getBalance(),
                account.getInterestAmount()
        );
    }
}
