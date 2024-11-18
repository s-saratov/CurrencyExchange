package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Transaction {

    private static final AtomicInteger idCounter = new AtomicInteger(0);

    private int transactionID;
    private int sourceAccountID;
    private int targetAccountID;
    private TransactionType transactionType;
    private BigDecimal amount;
    private final double feeRate = 1.5;
    private LocalDateTime timestamp;

   //constructors
    public Transaction(){

    }

    public Transaction(int sourceAccountID, int targetAccountID, TransactionType transactionType, BigDecimal amount) {
        this.transactionID = idCounter.incrementAndGet();
        this.sourceAccountID = sourceAccountID;
        this.targetAccountID = targetAccountID;
        this.transactionType = transactionType;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    //getters
    public int getTransactionID() {
        return transactionID;
    }

    public int getSourceAccountID() {
        return sourceAccountID;
    }

    public int getTargetAccountID() {
        return targetAccountID;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public double getFeeRate() {
        return feeRate;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    //setters
    public void setSourceAccountID(int sourceAccountID) {
        this.sourceAccountID = sourceAccountID;
    }

    public void setTargetAccountID(int targetAccountID) {
        this.targetAccountID = targetAccountID;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    //methods
    @Override
    public String toString() {
        return "\nTransaction{" +
                "transactionID: " + transactionID +
                ", sourceAccountID: " + sourceAccountID +
                ", targetAccountID: " + targetAccountID +
                ", transactionType: " + transactionType +
                ", amount: " + amount +
                ", feeRate: " + feeRate +
                ", timestamp: " + timestamp +
                '}';
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction that)) return false;

        return transactionID == that.transactionID && sourceAccountID == that.sourceAccountID && targetAccountID == that.targetAccountID && Double.compare(feeRate, that.feeRate) == 0 && transactionType == that.transactionType && Objects.equals(amount, that.amount) && Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        int result = transactionID;
        result = 31 * result + sourceAccountID;
        result = 31 * result + targetAccountID;
        result = 31 * result + Objects.hashCode(transactionType);
        result = 31 * result + Objects.hashCode(amount);
        result = 31 * result + Double.hashCode(feeRate);
        result = 31 * result + Objects.hashCode(timestamp);
        return result;
    }
}


