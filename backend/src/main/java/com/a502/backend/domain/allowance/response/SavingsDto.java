package com.a502.backend.domain.allowance.response;

import com.a502.backend.application.entity.Account;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class SavingsDto {
    private String accountUuid;
    private String name;
    private int Amount;
    private boolean hasOverdue;

    @Builder
    public SavingsDto(String accountUuid, String name, int amount, boolean hasOverdue) {
        this.accountUuid = accountUuid;
        this.name = name;
        Amount = amount;
        this.hasOverdue = hasOverdue;
    }

    public static List<SavingsDto> convertFromAcounts(List<Account> savings) {
        List<SavingsDto> result = new ArrayList<>();

        for (Account saving : savings) {
            result.add(convertFromAcount(saving));
        }

        return result;
    }

    private static SavingsDto convertFromAcount(Account saving) {

        return SavingsDto.builder()
                .accountUuid(saving.getAccountUuid().toString())
                .name(saving.getSavings().getName())
                .amount(saving.getPaymentAmount())
                .hasOverdue(false)
                .build();
    }
}
