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

    // Поля

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

    // Конструктор с параметром статуса счёта
    public Account(LocalDate creationDate, int accountId, User owner, Currency currency, BigDecimal balance, AccountStatus status) {
        this.accountId = accountId;
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

    public int getAccountId() {
        return accountId;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    // Методы

    // Возвращает строковое представление экземпляра класса
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

    // Проверяет равенство двух счетов, возвращает статус
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

    // Возвращает хэш-код
    @Override
    public int hashCode() {
        return Objects.hash(accountId, owner, currency, balance, creationDate, status);
    }
}
