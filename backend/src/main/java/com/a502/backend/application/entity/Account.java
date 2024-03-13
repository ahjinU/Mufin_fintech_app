package com.a502.backend.application.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private int accountId;

    @Column(name = "account_uuid")
    private byte[] accountUuid;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "balance")
    private Integer balance;

    @Column(name = "state")
    private String state;

    @Column(name = "interestAmount")
    private Integer interestAmount; //이자수령액

    @Column(name = "type")
    private String type;

    @Column(name = "payment_amount")
    private Integer paymentAmount;

    @Column(name = "payment_date")
    @Temporal(TemporalType.DATE)
    private Date paymentDate;

    @Column(name = "payment_cycle")
    private Integer paymentCycle;

    @Column(name = "password")
    private Integer password;

    @Column(name = "incorrect_cnt")
    private Integer incorrectCount;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "saving_id")
    private Savings savings;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Account(int accountId, byte[] accountUuid, String accountNumber, Integer balance, String state, Integer interestAmount, String type, Integer paymentAmount, Date paymentDate, Integer paymentCycle, Integer password, Integer incorrectCount, Boolean isDeleted, Savings savings, User user) {
        this.accountId = accountId;
        this.accountUuid = accountUuid;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.state = state;
        this.interestAmount = interestAmount;
        this.type = type;
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
        this.paymentCycle = paymentCycle;
        this.password = password;
        this.incorrectCount = incorrectCount;
        this.isDeleted = isDeleted;
        this.savings = savings;
        this.user = user;
    }
}
