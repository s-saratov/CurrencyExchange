package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;


public class Transaction {

    private static final AtomicInteger idCounter = new AtomicInteger(0);

    private int userID;
    private final int transactionID;
    private int sourceAccountID;
    private int targetAccountID;
    private TransactionType transactionType;
    private BigDecimal amount;
    private LocalDateTime timestamp;

    private CurrencyRate currencyRate;

    //constructors

    //для зачисления и списания
    public Transaction(BigDecimal amount) {

        this.transactionID = idCounter.incrementAndGet();
        //TODO привязать к аккаунту обменки
        //this.targetAccountID = accountID;
        this.transactionType = TransactionType.FEE;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    //для зачисления и списания
    public Transaction(int userID, int accountID, TransactionType transactionType, BigDecimal amount) {

        this.userID = userID;
        this.transactionID = idCounter.incrementAndGet();
        this.transactionType = transactionType;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();

        // Разветвление по типу транзакции
        if (transactionType == TransactionType.DEPOSIT) {
            this.targetAccountID = accountID;
        } else if (transactionType==TransactionType.WITHDRAW){
            this.sourceAccountID = accountID;
        } else {
            System.out.println("Unsupported transaction type: " + transactionType);
        }

    }

    //для обменных операций
    public Transaction(int userID, int sourceAccountID, int targetAccountID, BigDecimal amount) {

        this.userID = userID;
        this.transactionID = idCounter.incrementAndGet();
        this.sourceAccountID = sourceAccountID;
        this.targetAccountID = targetAccountID;
        this.transactionType = transactionType.TRANSFER;
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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public CurrencyRate getCurrencyRate() {
        return currencyRate;
    }

    public int getUserID() {
        return userID;
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
        return "\nTransaction {" +
                "transactionID :" + transactionID +
                ", sourceAccountID: " + sourceAccountID +
                ", targetAccountID:" + targetAccountID +
                ", transactionType: " + transactionType +
                ", amount: " + amount +
                ", currencyRate: " + currencyRate +
                ", timestamp: " + timestamp +
                '}';
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;

        Transaction that = (Transaction) o;
        return transactionID == that.transactionID && sourceAccountID == that.sourceAccountID && targetAccountID == that.targetAccountID && transactionType == that.transactionType && Objects.equals(amount, that.amount) && Objects.equals(timestamp, that.timestamp) && Objects.equals(currencyRate, that.currencyRate);
    }

    @Override
    public int hashCode() {
        int result = transactionID;
        result = 31 * result + sourceAccountID;
        result = 31 * result + targetAccountID;
        result = 31 * result + Objects.hashCode(transactionType);
        result = 31 * result + Objects.hashCode(amount);
        result = 31 * result + Objects.hashCode(timestamp);
        result = 31 * result + Objects.hashCode(currencyRate);
        return result;
    }
}


