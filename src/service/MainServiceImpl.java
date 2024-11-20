package service;

import model.*;
import repository.UserRepository;
import utils.validator.UserValidator;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Реализация MainService для управления пользователями, счетами и транзакциями.
 */
public class MainServiceImpl implements MainService {

    // ================= CREATE =================

    //-----------------------Account----------------------------
    @Override
    public Account addAccount(User owner, CustCurrency custCurrency, BigDecimal initialBalance) {
        return null;
    }

    //----------------------Currency-----------------------------
    @Override
    public boolean addCurrency(String currencyCode, String currencyName) {
        return false;
    }
    //----------------------CurrencyRate---------------------------
    @Override
    public boolean setExchangeRate(String currencyCode, BigDecimal rate) {
        return false;
    }

    //---------------------Transaction—----------------------------
    @Override
    public boolean depositMoney(int accountID, BigDecimal amount) {
        return false;
    }

    @Override
    public boolean withdrawMoney(int id, BigDecimal amount) {
        return false;
    }

    @Override
    public boolean transferMoney(double amount, int sourceAccountID, int targetAccountID) {
        return false;
    }

    @Override
    public BigDecimal toEUR(CustCurrency custCurrency, BigDecimal amount) {
        return null;
    }

    /*
    @Override
    public BigDecimal calculateFee(BigDecimal amount, double feeRate) {
        return null;
    }
     */

    @Override
    public List<Transaction> getTransactionHistoryByAccountID(int accountID) {
        return List.of();
    }

    @Override
    public List<Transaction> getTransactionHistoryByUserID(int userID) {
        return List.of();
    }

    // ======================= READ ============================

/*
        @Override
        public Map<Integer, User> getUsersByRole(UserRole... roles) {
            // Преобразуем массив ролей в Set для удобства поиска
            Set<UserRole> roleSet = new HashSet<>(Arrays.asList(roles));

            // Фильтруем пользователей по их роли и собираем их в карту
            return userRepository.getAllUsers().stream()
                    .filter(user -> roleSet.contains(user.getRole())) // Фильтрация по роли
                    .collect(Collectors.toMap(User::getUserID, user -> user));


 */


    //—-----------------------Account----------------------------
    @Override
    public Map<Integer, User> getAllAccounts() {
        return Map.of();
    }

    @Override
    public Map<Integer, User> getAllAccountsSortedByOwner() {
        return Map.of();
    }

    @Override
    public Map<Integer, Account> getUserAccountsByUserID(int userID) {
        return Map.of();
    }

    @Override
    public Account getAccountByID(int accountID) {
        return null;
    }
    //—-----------------------CurrencyRate—----------------------------
    @Override
    public Map<CustCurrency, Double> getCurrencyRates(CustCurrency custCurrency) {
        return Map.of();
    }

    @Override
    public boolean closeAccount(int accountID) {
        return false;
    }

    // ======================DELETE=================================

    @Override
    public Account deleteAccountByID(int accountID) {
        return null;
    }
}
