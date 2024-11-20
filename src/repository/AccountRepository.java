package repository;

import model.Account;
import model.CustCurrency;
import model.CustCurrency;
import model.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface AccountRepository {

    // CREATE

    // Добавляет счёт в общий список
    void addAccount(User owner, CustCurrency currency, BigDecimal initialBalance);

    // READ

    // Возвращает карту всех счетов
    public Map<Integer, List<Account>> getAccounts();

    // Возвращает счёт по ID
    Account getByID(int id);

    // Возвращает список счетов по владельцу
    List<Account> getAccountsByOwner(int ownerID);

    // Возвращает список счетов по валюте
    List<Account> getAccountsByCurrency(CustCurrency currency);

    // DELETE

    // Удаляет счёт
    void deleteAccount(int id);

}
