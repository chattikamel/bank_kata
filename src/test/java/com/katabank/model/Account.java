package com.katabank.model;

import java.math.BigDecimal;
import java.util.List;

public class Account {

    private BigDecimal balance;

    private List<Operation> operations;

    public void setBalance(BigDecimal blance) {
        this.balance = blance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void makeDeposit(BigDecimal amount) {
        balance = balance.add(amount);
    }

    public void makeWithdrawal(BigDecimal amount) {
        balance = balance.subtract(amount);
    }

    public void printOperations(String outputStream) {
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public Operation getLastOperation() {
        return operations.get(operations.size()-1);
    }
}
