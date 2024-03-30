package com.a502.backend.domain.user.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserMyPageResponse {
    String name;
    boolean isParent;
    String accountNumber;
    int balance;
    int savings;
    int monthAmounts;
    int ranking;
    int chocochip;
    int totalIncome;
    int totalPrice;
    double totalIncomePercent;
}
