package repository;

import model.Account;
import model.AccountStatus;
import model.Currency;
import model.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface AccountRepository {

    // === CREATE ===

    // Добавляет счёт в общую карту
    Account addAccount(int userID, String currencyCode, BigDecimal initialBalance);

    // Добавляет счёт в общий список (c указанием даты создания и статуса счёта)
    Account addAccount(int userID,
                       LocalDate creationDate,
                       String currencyCode,
                       BigDecimal initialBalance,
                       AccountStatus status);

    // === READ ===

    // Возвращает карту всех счетов
    public Map<Integer, List<Account>> getAccounts();

    // Возвращает счёт по ID
    Account getByID(int id);

    // Возвращает список счетов по владельцу
    List<Account> getAccountsByOwner(int ownerID);

    // Возвращает список счетов по валюте
    List<Account> getAccountsByCurrency(Currency currency);

    // DELETE

    // Удаляет счёт
    void deleteAccount(int id);

}