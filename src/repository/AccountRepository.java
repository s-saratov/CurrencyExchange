package repository;

import model.Account;
import model.CustCurrency;
import model.User;

import java.math.BigDecimal;
import java.util.List;

public interface AccountRepository {

    // CREATE

    // Добавляет счёт в общий список
    void addAccount(User owner, BigDecimal initialBalance);

    // READ

    // Возвращает список всех счетов
    List<Account> getAllAccounts();

    // Возвращает счёт по ID
    Account getByID(int id);

    // Возвращает список счетов по владельцу
    List<Account> getAccountsByOwner(int ownerID);

    // Возвращает список счетов по валюте
    List<Account> getAccountsByCurrency(CustCurrency currency);

    // DELETE

    // Удаляет счёт
    void deleteAccount(int accountID);

}
