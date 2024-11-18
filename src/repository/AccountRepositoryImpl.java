package repository;

import model.Account;
import model.CustCurrency;
import model.User;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class AccountRepositoryImpl implements AccountRepository{

    // Поля
    Map<Integer, Account> accounts;     // карта счетов
    AtomicInteger currentID;            // объект, отвечающий за генерацию уникальных ID

    // Конструктор
    public AccountRepositoryImpl(Map<Integer, Account> accounts, AtomicInteger currentID) {
        this.accounts = new HashMap<>();
    }

    // Методы

    // CREATE

    // Добавляет счёт в общий список
    @Override
    public void addAccount(User owner, BigDecimal initialBalance) {

    }

    // READ

    // Возвращает список всех счетов
    @Override
    public List<Account> getAllAccounts() {
        return List.of();
    }

    // Возвращает счёт по ID
    @Override
    public Account getByID(int id) {
        return null;
    }

    // Возвращает список счетов по владельцу
    @Override
    public List<Account> getAccountsByOwner(int ownerID) {
        return List.of();
    }

    // Возвращает список счетов по валюте
    @Override
    public List<Account> getAccountsByCurrency(CustCurrency currency) {
        return List.of();
    }

    @Override
    public String toString() {
        return "AccountRepositoryImpl{" +
                "accounts=" + accounts +
                ", currentID=" + currentID +
                '}';
    }

    // DELETE

    // Удаляет счёт
    @Override
    public void deleteAccount(int id) {

    }
}