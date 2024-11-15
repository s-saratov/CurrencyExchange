package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.Objects;

/**
 * @author olgakharina
 * @date 15.11.24
 */
public class Account {

    // Идентификационный номер счета пользователя
    private final int accountId;

    // Дата создания счета
    private final LocalDate creationDate;

    // Владелец счета
    private User owner;

    // Валюта счета
    private Currency currency;

    // Баланс счета
    private BigDecimal balance;

    // Статус счета
    private AccountStatus status;

    // Стандартный конструктор
    public Account(int accountId, LocalDate creationDate, User owner, Currency currency) {
        this.accountId = accountId;
        this.creationDate = creationDate;
        this.owner = owner;
        this.currency = currency;
    }

    // Конструктор с параметром статуса счета
    public Account(LocalDate creationDate, int accountId, User owner, Currency currency, BigDecimal balance, AccountStatus status) {
        this.accountId = accountId;
        // Устанавливается текущая дата
        this.creationDate = LocalDate.now();
        this.owner = owner;
        this.currency = currency;
        this.balance = balance;
        /* Если статус не была задан при создании пользователя - по умолчанию "ACTIVE" */
        this.status = status != null ? status : AccountStatus.ACTIVE;
    }

    // Метод toString
    @Override
    public String toString() {
        return "Account {" +
                "id=" + accountId +
                ", owner=" + owner.getName() + // Предполагается, что в User есть метод getName()
                ", currency=" + currency +
                ", balance=" + balance +
                ", creationDate=" + creationDate +
                ", status=" + status +
                '}';
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
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

    // Метод equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;                  // Сравнение с самим собой
        if (obj == null || getClass() != obj.getClass()) return false; // Проверка на null и класс
        Account account = (Account) obj;
        return accountId == account.accountId &&
                Objects.equals(owner, account.owner) &&
                currency == account.currency &&
                Objects.equals(balance, account.balance) &&
                Objects.equals(creationDate, account.creationDate) &&
                status == account.status;
    }

    // Метод hashCode
    @Override
    public int hashCode() {
        return Objects.hash(accountId, owner, currency, balance, creationDate, status);
    }
}
