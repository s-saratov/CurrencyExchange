package repository;

import model.Account;

import java.util.Objects;

public interface UserRepository {

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