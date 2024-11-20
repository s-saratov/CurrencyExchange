package repository;

import model.Transaction;
import model.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository {

    //CREATE
    //зачисление комиссии на сервисный счёт компании
    void feeDepositTransaction(BigDecimal amount);

    //добавить трансакцию в список трансакций счёта
    void addTransactionToAccountTransactionsList(int accountID, Transaction transaction);

    //зачисление средств на счёт
    void depositTransaction(int userID, int accountID, TransactionType transactionType, BigDecimal amount);

    //списание средств со счёта
    void withdrawTransaction(int userID, int accountID, TransactionType transactionType, BigDecimal amount);

    //пересчёт любой валюты в базовую валюту - евро
    BigDecimal convertToBaseCurrency(String fromCurrencyCode, BigDecimal amount);

    //обмен валют
    boolean exchangeCurrency(int userID, int fromAccountId, int toAccountId, BigDecimal amount);

    //READ
    //найти операцию по id
    Transaction findTransaction(int id);


    //посмотреть список операций по юзеру
    List<Transaction> getTransactionsByUserId(int userId);

    //посмотреть список всех операций
    List<Transaction> getAllTransactions(String currentCode);

    /*
    //посмотреть список всех операций по валюте
    List<Transaction> getAllTransactionsByCurrency(String currentCode);
     */

    //посмотреть список операций по дате
    List<Transaction> getTransactionsByDate(LocalDate date);

    //посмотреть возможность приобретения опред-ого количества опред-й валюты
    boolean canPurchaseCurrency(int accountId, String targetCurrency, BigDecimal targetAmount);

    //DELETE
    //удалить операцию (доступен администратору)
    boolean deleteTransaction(int transactionId);

}
