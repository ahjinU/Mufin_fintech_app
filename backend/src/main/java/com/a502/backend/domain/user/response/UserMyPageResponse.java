package com.a502.backend.domain.user.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserMyPageResponse {
    String name;
    @JsonProperty(value = "isParent")
    Boolean isParent;
    String accountNumber;
    int balance;
    int savings;
    int monthAmounts;
    int ranking;
    int chocochip;
    int totalIncome;
    int totalPrice;
    String totalIncomePercent;
}
