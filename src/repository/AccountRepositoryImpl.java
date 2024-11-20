package repository;

import model.*;
import model.CustCurrency;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class AccountRepositoryImpl implements AccountRepository {
    // Поля
    private final AtomicInteger currentID = new AtomicInteger(1); // объект, отвечающий за генерацию уникальных ID
    Map<Integer, List<Account>> accounts;   // карта счетов

    //accountId
    private Map<Integer, List<Transaction>> accountTransactions; //карта трансакций счёта

    UserRepository userRepository;
    CustCurrencyRepository currencyRepository;

    // Конструктор
    public AccountRepositoryImpl() {
        // Создаём карту счетов
        this.accounts = new HashMap<>();

        User serviceUser = userRepository.getUserByEmail("bogdan@example.com");
        CustCurrency baseCurrency = currencyRepository.getCurrencyByCode("EUR");

        Account serviceAccount = new Account(
                777,
                serviceUser,
                baseCurrency,
                BigDecimal.ZERO,
                AccountStatus.SYSTEM
        );
        //добавляем этот счёт в карту счётов
        List<Account> serviceAccounts = new ArrayList<>();
        serviceAccounts.add(serviceAccount.getAccountID(), serviceAccount);
        accounts.put(serviceAccount.getAccountID(), serviceAccounts);
    }

    // Методы

    // CREATE

    // Добавляет счёт в общий список
    @Override
    public void addAccount(User owner, CustCurrency currency, BigDecimal initialBalance) {
        Account newAccount = new Account(
                this.currentID.getAndIncrement(),
                owner,
                currency,
                initialBalance,
                AccountStatus.ACTIVE
        );
        accounts.computeIfAbsent(owner.getUserID(), k -> new ArrayList<>()).add(newAccount);
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

    @Override
    public List<Transaction> getAccountTransactionBuAccountId(int accountID) {
        //проверяем, существует ли счёт с таким accountID в карте
        if (accountTransactions.containsKey(accountID)) {
            // Возвращаем список транзакций для данного accountID
            return accountTransactions.get(accountID);
        } else {
            //если счёт не найден, возвращаем пустой список
            System.out.println("No transactions found for account ID: " + accountID);
            return List.of();

        }
    }

    @Override
    public void addAccountTransactionsList(int accountID, List<Transaction> transactions) {
        // Проверяем, существует ли список транзакций для данного accountID
        List<Transaction> transactionsList = accountTransactions.get(accountID);

        // Если список отсутствует, создаем его
        if (transactionsList == null) {
            transactionsList = new ArrayList<>();
            accountTransactions.put(accountID, transactionsList); // Добавляем новый список в карту
        }
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