package com.katabank.domain.model;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import static java.lang.String.format;

public class Operation {

    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd-mm-yyyy");

    private OperationType type;
    private Date date;
    private BigDecimal amount;
    private BigDecimal balance;


    public OperationType getType() {
        return type;
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
        return format("%s operation on %s of %d, balance is %d",
                getType(),
                SIMPLE_DATE_FORMAT.format(getDate()),
                getAmount().longValue(),
                getBalance().longValue());
    }

    public static final class OperationBuilder {
        private OperationType type;
        private Date date;
        private BigDecimal amount;
        private BigDecimal balance;

        private OperationBuilder() {
        }

        public static OperationBuilder anOperation() {
            return new OperationBuilder();
        }

        public OperationBuilder withType(OperationType type) {
            this.type = type;
            return this;
        }

        public OperationBuilder withDate(Date date) {
            this.date = date;
            return this;
        }

        public OperationBuilder withAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public OperationBuilder withBalance(BigDecimal balance) {
            this.balance = balance;
            return this;
        }

        public Operation build() {
            Operation operation = new Operation();
            operation.type = this.type;
            operation.amount = this.amount;
            operation.balance = this.balance;
            operation.date = this.date;
            return operation;
        }
    }
}
