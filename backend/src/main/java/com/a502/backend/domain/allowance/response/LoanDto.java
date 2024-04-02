package com.a502.backend.domain.allowance.response;

import com.a502.backend.application.entity.Loan;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class LoanDto {

    private String loanUuid;
    private String name;
    private int currentPaymentAmount;
    private int totalPaymentAmount;
    private boolean hasOverdue;


    @Builder
    public LoanDto(String loanUuid, String name, int currentPaymentAmount, int totalPaymentAmount, boolean hasOverdue) {
        this.loanUuid = loanUuid;
        this.name = name;
        this.currentPaymentAmount = currentPaymentAmount;
        this.totalPaymentAmount = totalPaymentAmount;
        this.hasOverdue = hasOverdue;
    }
    public static List<LoanDto> convertFromLoans(List<Loan> loans) {
        List<LoanDto> result = new ArrayList<>();
        for (Loan loan : loans) {
            result.add(convertFromLoan(loan));
        }

        return result;
    }

    public static LoanDto convertFromLoan(Loan loan) {
        boolean hasOverdue = false;
        if (loan.getOverdueCnt() > 0)
            hasOverdue = true;

        return LoanDto.builder()
                .loanUuid(loan.getLoanUuid().toString())
                .name(loan.getReason())
                .currentPaymentAmount(loan.getPaymentNowCnt() * (loan.getAmount() / loan.getPaymentTotalCnt()))
                .totalPaymentAmount(loan.getAmount())
                .hasOverdue(hasOverdue)
                .build();
    }
}