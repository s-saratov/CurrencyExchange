package repository;

import model.Account;
import model.AccountStatus;
import model.Currency;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class AccountRepositoryImpl implements AccountRepository {

    // Поля
    Map<Integer, List<Account>> accounts;                     // карта счетов
    AtomicInteger currentID = new AtomicInteger(1); // объект, отвечающий за генерацию уникальных ID

    // Конструктор
    public AccountRepositoryImpl() {
        // Создаём карту счетов
        this.accounts = new HashMap<>();
        this.addAccount(2, LocalDate.parse("2024-02-15"), "EUR", BigDecimal.valueOf(1500.00), AccountStatus.ACTIVE);
        this.addAccount(2, LocalDate.parse("2024-05-07"), "USD", BigDecimal.valueOf(2000.00), AccountStatus.ACTIVE);
    }

    // Методы

    // === CREATE ===

    // Добавляет счёт в общий список
    @Override
    public Account addAccount(int userID, String currencyCode, BigDecimal initialBalance) {
        Account newAccount = new Account(this.currentID.getAndIncrement(), currencyCode, initialBalance);
        // Получаем список счетов для данного userID или создаём новый, если списка нет
        accounts.computeIfAbsent(userID, k -> new ArrayList<>()).add(newAccount);
        return newAccount;
        // TODO: дописать проверки

    }

    // Добавляет счёт в общий список (c указанием даты создания и статуса счёта)
    @Override
    public Account addAccount(int userID,
                              LocalDate creationDate,
                              String currencyCode,
                              BigDecimal initialBalance,
                              AccountStatus status) {
        Account newAccount = new Account(this.currentID.getAndIncrement(), creationDate, currencyCode, initialBalance, status);
        // Получаем список счетов для данного userID или создаём новый, если списка нет
        accounts.computeIfAbsent(userID, k -> new ArrayList<>()).add(newAccount);
        return newAccount;
        // TODO: написать проверки

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
        // TODO: написать проверки
    }

    // Возвращает список счетов по владельцу
    @Override
    public List<Account> getAccountsByOwner(int ownerID) {
        return accounts.get(ownerID);
    }

    // Возвращает список счетов по валюте
    @Override
    public List<Account> getAccountsByCurrency(Currency currency) {
        return List.of();
        // TODO: написать метод
    }

    // Возвращает строковое представление экземпляра класса
    @Override
    public String toString() {
        return "AccountRepositoryImpl{" +
                "accounts=" + accounts +
                ", currentID=" + currentID +
                '}';
    }

    // === DELETE ===

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