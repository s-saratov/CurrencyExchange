package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import model.CustCurrency;

/**
 * @author olgakharina
 * @date 15.11.24
 */
public class Account {

    // Поля

    // Идентификационный номер счета пользователя
    private final int accountID;

    // Дата создания счета
    private final LocalDate creationDate;

    // Владелец счета
    private User owner;

    // Валюта счета
    private CustCurrency currency;

    // Баланс счета
    private BigDecimal balance;

    // Статус счета
    private AccountStatus status;

    // Стандартный конструктор
    public Account(int accountID, LocalDate creationDate, User owner, CustCurrency currency) {
        this.accountID = accountID;
        this.creationDate = creationDate;
        this.owner = owner;
        this.currency = currency;
    }

    // Конструктор с параметром статуса счёта
    public Account(LocalDate creationDate, int accountID, User owner, CustCurrency currency, BigDecimal balance, AccountStatus status) {
        this.accountID = accountID;
        // Устанавливается текущая дата
        this.creationDate = LocalDate.now();
        this.owner = owner;
        this.currency = currency;
        this.balance = balance;
        /* Если статус не был задан при создании пользователя - по умолчанию "ACTIVE" */
        this.status = status != null ? status : AccountStatus.ACTIVE;
    }

    // Геттеры и сеттеры

    public User getOwner() {
        return owner;
    }
    public void setOwner(User owner) {
        this.owner = owner;
    }

    public CustCurrency getCurrency() {
        return currency;
    }
    public void setCurrency(CustCurrency currency) {
        this.currency = currency;
    }

    public BigDecimal getBalance() {
        return balance;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public AccountStatus getStatus() {
        return status;
    }
    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public int getAccountID() {
        return accountID;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    // Методы

    // Возвращает строковое представление экземпляра класса
    @Override
    public String toString() {
        return "Account {" +

                "id=" + accountID +
                ", owner=" + owner.getName() + // Предполагается, что в User есть метод getCurrencyName()
                ", currency=" + currency +
                ", balance=" + balance +
                ", creationDate=" + creationDate +
                ", status=" + status +
                '}';
    }

}
