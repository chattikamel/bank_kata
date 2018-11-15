package com.katabank.domain.model;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Operation {
    private OperationType type;
    private Date date;
    private BigDecimal amount;
    private BigDecimal balance;


    public void setType(OperationType type) {
        this.type = type;
    }

    public OperationType getType() {
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

    public Date getDate() {
        return date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getBalance() {
        return balance;
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

    @Override
    public String toString() {
        return String.format("%s operation on %s of %d, balance is %d", getType(), new SimpleDateFormat("dd-mm-yyyy").format(getDate()), getAmount().longValue(), getBalance().longValue());
    }
}
