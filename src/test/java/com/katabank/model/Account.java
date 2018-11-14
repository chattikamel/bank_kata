package com.katabank.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Account {

    private BigDecimal balance;

    private List<Operation> operations;

    public Account() {
        this.operations = new ArrayList<>();
    }

    public void setBalance(BigDecimal blance) {
        this.balance = blance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void makeDeposit(BigDecimal amount, Date operationDate) {
        balance = balance.add(amount);
        Operation operation =  new Operation();
        operation.setAmount(amount);
        operation.setDate(operationDate);
        operation.setBalance(balance);
        operation.setType("deposit");
        operations.add(operation);
    }

    public void makeWithdrawal(BigDecimal amount, Date operationDate) {
        balance = balance.subtract(amount);
        Operation operation =  new Operation();
        operation.setAmount(amount);
        operation.setDate(operationDate);
        operation.setBalance(balance);
        operation.setType("withdrawal");
        operations.add(operation);
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
