package service;

import model.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface MainService  {

    // ================= CREATE =================



    //-----------------------Account----------------------------
    // Добавляет новый счёт в репозиторий (список)
    // Может потребоваться валидация на наличие счета в такой валюте для пользователя.
    Account addAccount(User owner, CustCurrency custCurrency, BigDecimal initialBalance);

    //----------------------Currency-----------------------------
    // Добавляет валюту (доступен только администратору)
    boolean addCurrency(String currencyCode, String currencyName);

    //----------------------CurrencyRate---------------------------
    // Устанавливает курс валюты (доступен администратору)
    boolean setExchangeRate(String currencyCode, BigDecimal rate);

    //---------------------Transaction—----------------------------
    //Осуществляет поступление указанной суммы на счёт по ID, возвращает статус успеха операции
    boolean depositMoney(int accountID, BigDecimal  amount);

    //Осуществляет снятие указанной суммы со счёта по ID, возвращает статус успеха операции
    boolean withdrawMoney(int id, BigDecimal amount);

    //Осуществляет перевод указанной суммы со счёта-донора на счёт-реципиент (по ID), возвращает статус успеха операции
    boolean transferMoney(double amount, int sourceAccountID, int targetAccountID);

    //Осуществляет пересчет из любой валюты в евро, возвращает сумму в BigDecimal
    BigDecimal toEUR(CustCurrency custCurrency, BigDecimal amount);

    /*
    //Вычисляет комиссию по операции в формате BigDecimal
    BigDecimal calculateFee(BigDecimal amount, double feeRate);
    */

    // Показать историю транзакций по ID счета
    List<Transaction> getTransactionHistoryByAccountID(int accountID);

    // Показать историю транзакций по ID пользователя
    List<Transaction> getTransactionHistoryByUserID(int userID);


    // ======================= READ ============================
    // -----------------------User—-----------------------------


    //—-----------------------Account----------------------------
    //Возвращает список всех счетов компании в формате карты (для администратора)
    Map<Integer, User> getAllAccounts();

    //Возвращает карту счетов компании, отсортированную по пользователям
    Map<Integer, User> getAllAccountsSortedByOwner();

    //Возвращает карту счетов одного пользователя по ID пользователя
    Map<Integer, Account> getUserAccountsByUserID(int userID);

    //  Найти счёт по его ID
    Account getAccountByID(int accountID);

    //—-----------------------CurrencyRate—----------------------------
    // Посмотреть текущие курсы валют
    Map<CustCurrency, Double> getCurrencyRates(CustCurrency custCurrency);

    //Закрыть счёт (изменить статус на closed) по ID
    boolean closeAccount(int accountID);

    // ======================DELETE=================================

    // Удаляет счёт по id
    // Следует учитывать, что перед удалением нужно проверить отсутствие средств на счете.
    Account deleteAccountByID(int accountID);


}
