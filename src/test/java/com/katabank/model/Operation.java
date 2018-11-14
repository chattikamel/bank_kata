package com.katabank.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class Operation {
    private String type;
    private Date date;
    private BigDecimal amount;
    private BigDecimal balance;


    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return Objects.equals(type, operation.type) &&
                Objects.equals(date, operation.date) &&
                Objects.equals(amount, operation.amount) &&
                Objects.equals(balance, operation.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, date, amount, balance);
    }
}
