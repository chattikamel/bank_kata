package com.katabank.model;

import java.math.BigDecimal;

public class Account {

    private BigDecimal balance;

    public void setBalance(BigDecimal blance) {
        this.balance = blance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void makeDeposit(BigDecimal amount) {
        balance = balance.add(amount);
    }
}
