package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {

    // Поля

    private final int transactionID;                      // идентификационный номер
    private LocalDateTime timestamp;                // дата и время
    private TransactionType transactionType;        // тип
    private int sourceAccountID;                    // ID счёта-источника
    private int targetAccountID;                    // ID счёта-получателя
    private BigDecimal amount;
    private double feeRate;

    // Конструктор

    public Transaction(int transactionID,
                       LocalDateTime timestamp,
                       TransactionType transactionType,
                       int sourceAccountID,
                       int targetAccountID,
                       BigDecimal amount,
                       double feeRate) {
        this.transactionID = transactionID;
        this.timestamp = timestamp;
        this.transactionType = transactionType;
        this.sourceAccountID = sourceAccountID;
        this.targetAccountID = targetAccountID;
        this.amount = amount;
        this.feeRate = feeRate;
    }

    // Сеттеры

    public int getTransactionID() {
        return transactionID;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public int getSourceAccountID() {
        return sourceAccountID;
    }

    public int getTargetAccountID() {
        return targetAccountID;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public double getFeeRate() {
        return feeRate;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionID=" + transactionID +
                ", timestamp=" + timestamp +
                ", transactionType=" + transactionType +
                ", sourceAccountID=" + sourceAccountID +
                ", targetAccountID=" + targetAccountID +
                ", amount=" + amount +
                ", feeRate=" + feeRate +
                '}';
    }
}