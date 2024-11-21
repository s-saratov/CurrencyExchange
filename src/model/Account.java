package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Account {

    // Поля

    // Идентификационный номер счета пользователя
    private final int accountID;

    // Дата создания счета
    private final LocalDate creationDate;

    // Код валюты счета
    private String currencyCode;

    // Баланс счета
    private BigDecimal balance;

    // Статус счета
    private AccountStatus status;

    // Конструкторы

    // Стандартный (создаёт счёт текущей датой со статусом ACTIVE)
    public Account(int accountID, String currencyCode, BigDecimal balance) {
        this.accountID = accountID;
        this.creationDate = LocalDate.now();
        this.currencyCode = currencyCode; // TODO: сделать вставку кода через сеттер для проверки
        this.balance = balance;
        this.status = AccountStatus.ACTIVE;
    }

    // Полный (с указанием даты открытия и статуса счёта)
    public Account(int accountID, LocalDate creationDate, String currencyCode, BigDecimal balance, AccountStatus status) {
        this.accountID = accountID;
        this.creationDate = creationDate;
        this.currencyCode = currencyCode;
        this.balance = balance;
        this.status = AccountStatus.ACTIVE;
    }

    // Геттеры и сеттеры

    // TODO: добавить сеттеры по необходимости

    public int getAccountID() {
        return accountID;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    // Методы

    // Возвращает строковое представление экземпляра класса


    @Override
    public String toString() {
        return "Account{" +
                "accountID=" + accountID +
                ", creationDate=" + creationDate +
                ", currencyCode='" + currencyCode + '\'' +
                ", balance=" + balance +
                ", status=" + status +
                '}';
    }
}