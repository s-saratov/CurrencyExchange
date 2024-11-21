package service;

import model.Account;
import model.User;
import model.UserRole;

import java.math.BigDecimal;
import java.util.List;

public interface MainService {

    // === CREATE ===

    // Добавляет новую валюту
    public boolean addCurrency(String currencyCode, String currencyName, double rate);

    // Добавляет счёт банковский счёт для пользователя по ID
    public Account addAccount(int userID, String currencyCode, BigDecimal initialBalance);

    // === READ ===

    // Возвращает объект пользователя по ID
    public User getUserByID(int userID);

    // Возвращает активного пользователя
    public User getActiveUser();

    // Возвращает список пользователей по заданным ролям
    public List<User> getUsersByRole(UserRole... roles);

    // Возвращает объект счёта по ID
    public Account getAccountByID(int accountID);

    // Возвращает текущий курс валюты по коду
    public double getCurrencyRate(String currencyCode);

    // Возвращает boolean, корректен ли код валюты
    public boolean isCurrencyCodeCorrect(String currencyCode);

    // Возвращает код валюты по ID счёта
    public String getCurrencyCode(int accountID);

    // Выводит в консоль строковое представление курсов валют
    public void printCurrencyRates();

    // Выводит в консоль строковое представление счетов пользователя
    public void printUserAccounts();

    // Выводит в консоль строковое представление счетов пользователя (за исключением id)
    public void printUserAccounts(int id);

    // Выводит в консоль строковое представление доступных в системе валют
    public void printCurrencies();


    // === UPDATE ===

    // Осуществляет перевод суммы денег с одного счёта (по ID) на другой (по ID)
    public void transferMoney(int sourceAccountID, int targetAccountID, BigDecimal amount);

    // Осуществляет внесение денег на счёт
    public void depositTransfer(int sourceAccountID, BigDecimal amount);

    // Осуществляет снятие денег на счёт
    public void withdrawMoney(int sourceAccountID, BigDecimal amount);

    // Регистрирует пользователя на основании переданных имени, адреса электронной почты и пароля, возвращает экземпляр класса
    public User registerUser(String name, String email, String password);

    // Осуществляет вход пользователя в систему и возвращает статус успеха операции
    public boolean loginUser(String email, String password);

    // Осуществляет выход пользователя из системы
    public void logout();

}