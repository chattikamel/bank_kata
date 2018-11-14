package com.katabank.model;

import java.math.BigDecimal;
import java.util.Date;

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
}
