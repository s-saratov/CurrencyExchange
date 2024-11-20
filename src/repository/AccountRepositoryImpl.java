package repository;

import model.*;
import model.CustCurrency;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class AccountRepositoryImpl implements AccountRepository {

    // Поля
    Map<Integer, List<Account>> accounts;   // карта счетов
    AtomicInteger currentID;                // объект, отвечающий за генерацию уникальных ID

    // Конструктор
    public AccountRepositoryImpl(Map<Integer, Account> accounts, AtomicInteger currentID) {
        // Создаём карту счетов
        this.accounts = new HashMap<>();
        // TODO: написать создание счетов по умолчанию
    }

    // Методы

    // CREATE

    // Добавляет счёт в общий список
    @Override
    public void addAccount(User owner, CustCurrency currency, BigDecimal initialBalance) {
        Account newAccount = new Account(
                LocalDate.now(),
                this.currentID.getAndIncrement(),
                owner,
                currency,
                initialBalance,
                AccountStatus.ACTIVE
        );
        accounts.computeIfAbsent(owner.getId(), k -> new ArrayList<>()).add(newAccount);
        // TODO: дописать проверки
    }

    // READ

    // Возвращает карту всех счетов
    public Map<Integer, List<Account>> getAccounts() {
        return accounts;
    }

    // Возвращает счёт по ID
    @Override
    public Account getByID(int id) {
        for (List<Account> accounts : accounts.values()) {
            for (Account account : accounts) {
                if (account.getAccountID() == id) return account;
            }
        }
        return null;
    }

    // Возвращает список счетов по владельцу
    @Override
    public List<Account> getAccountsByOwner(int ownerID) {
        return accounts.get(ownerID);
    }

    // Возвращает список счетов по валюте
    @Override
    public List<Account> getAccountsByCurrency(CustCurrency currency) {
        List<Account> result = new ArrayList<>();
        for (List<Account> accounts : accounts.values()) {
            for (Account account : accounts) {
                if (account.getCurrency().equals(currency)) result.add(account);
            }
        }
        return result;
    }

    // Возвращает строковое представление экземпляра класса
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
        for (List<Account> accounts : accounts.values()) {
            for (Account account : accounts) {
                if (account.getAccountID() == id) accounts.remove(account);
            }
        }
        // TODO: добавить проверки
    }
}