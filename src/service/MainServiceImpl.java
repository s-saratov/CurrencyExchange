package service;

import model.*;
import repository.UserRepository;
import utils.validator.UserValidator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MainServiceImpl implements MainService {

    private UserRepository userRepository;

    // ================= CREATE =================

    //-----------------------User—-----------------------------
    @Override
    public User registerUser(String name, String email, String password) {
        try { // Валидация данных
            UserValidator.isUsernameValid(name);
            UserValidator.isEmailValid(email);
            UserValidator.isPasswordValid(password);

            // Проверка, существует ли email
            if (userRepository.isEmailExists(email)) {
                System.out.println("Пользователь с таким email уже существует.");
                return null;
            }
            // Создание и сохранение нового пользователя
            return userRepository.addUser()
                    addUser(currentID.getAndIncrement(), name, email, password);
        } catch (Exception e) {
            System.out.println("Ошибка регистрации пользователя: " + e.getMessage());
            return null;
        }








        return null;
    }

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
    // -----------------------User—-----------------------------
    @Override
    public User getUserByID(int userID) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }

    @Override
    public User getActiveUser(int userID) {
        return null;
    }

    @Override
    public Map<Integer, User> getUsersByRole(UserRole... roles) {
        return Map.of();
    }

    @Override
    public boolean loginUser(String email, String password) {
        return false;
    }

    @Override
    public void logoutUser() {

    }
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
    public boolean deleteUserByID(int userID) {
        return false;
    }

    @Override
    public Account deleteAccountByID(int accountID) {
        return null;
    }
}
