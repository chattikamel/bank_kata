package com.katabank.domain.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
        logOperation(amount, operationDate, OperationType.deposit);
    }


    public void makeWithdrawal(BigDecimal amount, Date operationDate) {
        balance = balance.subtract(amount);
        logOperation(amount, operationDate, OperationType.withdrawal);
    }

    private void logOperation(BigDecimal amount, Date operationDate, OperationType deposit) {
        operations.add(Operation.OperationBuilder.anOperation()
                .withType(deposit).withBalance(balance).withDate(operationDate)
                .withAmount(amount).build());
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public Operation getLastOperation() {
        return operations.get(operations.size() - 1);
    }

    public String printHistory() {
        return operations.stream().map(operation -> operation.toString()).collect(Collectors.joining("\n"));
    }
}

