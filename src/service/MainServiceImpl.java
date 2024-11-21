package service;

import model.Account;
import model.Currency;
import model.User;
import model.UserRole;
import repository.*;
import utils.validator.EmailValidateException;
import utils.validator.LoginValidator;
import utils.validator.PasswordValidateException;

import javax.management.relation.Role;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

public class MainServiceImpl implements MainService {

    // Поля

    private final AccountRepository accountRepository;          // репозиторий счетов
    private final CurrencyRepository currencyRepository;        // репозиторий валют
    private final RateHistoryRepository rateHistoryRepository;  // репозиторий истории валют
    private final UserRepository userRepository;                // репозиторий пользователей
    private User activeUser;                                    // действующий пользователь

    // Конструктор

    public MainServiceImpl(AccountRepository accountRepository,
                           CurrencyRepository currencyRepository,
                           RateHistoryRepository rateHistoryRepository,
                           UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.currencyRepository = currencyRepository;
        this.rateHistoryRepository = rateHistoryRepository;
        this.userRepository = userRepository;
        this.activeUser = userRepository.getUserByID(1);    // TODO: изменить в дальнейшем
    }

    // Методы

    // === CREATE ===

    // Регистрирует пользователя на основании переданных имени, адреса электронной почты и пароля, возвращает экземпляр класса
    @Override
    public User registerUser(String name, String email, String password) {
        if (email == null || password == null) {
            return null;
        }
        try {
            LoginValidator.isEmailValid(email);
        } catch (EmailValidateException e) {
            return null;
        }
        try {
            LoginValidator.isPasswordValid(email);
        } catch (PasswordValidateException e) {
            return null;
        }
        if (userRepository.isEmailExists(email)) {
            return null;
        }
        return userRepository.addUser(name, email, password);
    }

    // Добавляет новую валюту
    @Override
    public boolean addCurrency(String currencyCode, String currencyName, double rate) {
        return currencyRepository.addCurrency(currencyCode, currencyName, rate);
    }

    // Добавляет счёт банковский счёт для пользователя по ID
    @Override
    public Account addAccount(int userID, String currencyCode, BigDecimal initialBalance) {
        return accountRepository.addAccount(userID, currencyCode, initialBalance);
    }



    // === READ ===

    // Возвращает объект пользователя по ID
    @Override
    public User getUserByID(int userID) {
        return userRepository.getUserByID(userID);
    }

    // Возвращает активного пользователя
    @Override
    public User getActiveUser() {
        return activeUser;
    }

    // Возвращает список пользователей по заданным ролям
    @Override
    public List<User> getUsersByRole(UserRole... roles) {
        return userRepository.getUsersByRole(roles);
    }

    // Возвращает объект счёта по ID
    @Override
    public Account getAccountByID(int accountID) {
        return accountRepository.getByID(accountID);
    }

    // Возвращает текущий курс валюты по коду
    @Override
    public double getCurrencyRate(String currencyCode) {
        return currencyRepository.getCurrencyMap().get(currencyCode).getRate().getCurrencyRate();
        // TODO: дописать проверку переданного значения
    }

    // Возвращает boolean, корректен ли код валюты
    public boolean isCurrencyCodeCorrect(String currencyCode) {
        return currencyRepository.getAllCurrencies().containsKey(currencyCode);
    }

    // Возвращает код валюты по ID счёта
    @Override
    public String getCurrencyCode(int accountID) {
        return getAccountByID(accountID).getCurrencyCode();
    }

    // Выводит в консоль строковое представление курсов валют
    public void printCurrencyRates() {

        Map<String, Currency> currencies = currencyRepository.getCurrencyMap();

        System.out.println("\n\u001B[33mТекущие курсы валют:\u001B[0m");
        for (Map.Entry<String, Currency> entry : currencies.entrySet()) {
            if (entry.getKey().equals("EUR")) continue;
            else {
                // System.out.println(entry.getKey() + " " + entry.getValue().getCurrencyName() + " " + entry.getValue().getRate().getCurrencyRate() + " Euro");
                System.out.printf("1 %-20s (%s) = %f Евро\n",
                        entry.getValue().getCurrencyName(),
                        entry.getKey(),
                        entry.getValue().getRate().getCurrencyRate());
            }
        }
    }

    // Выводит в консоль строковое представление счетов пользователя
    @Override
    public void printUserAccounts() {

        List<Account> accounts = accountRepository.getAccounts().get(getActiveUser().getUserID());

        System.out.printf(String.format("\u001B[33m%-5s %-9s %-10s\u001B[0m\n", "ID:", "Валюта:", "Остаток:"));
        for (Account account : accounts) {
            System.out.println(String.format("%-5s %-9s %-10s",
                    account.getAccountID(), account.getCurrencyCode(), account.getBalance()));
        }
        System.out.println();

    }

    // Выводит в консоль строковое представление счетов пользователя (за исключением id)
    @Override
    public void printUserAccounts(int id) {

        List<Account> accounts = accountRepository.getAccounts().get(getActiveUser().getUserID());

        System.out.printf(String.format("\u001B[33m%-5s %-9s %-10s\u001B[0m\n", "ID:", "Валюта:", "Остаток:"));
        for (Account account : accounts) {
            if (account.getAccountID() != id) {
                System.out.println(String.format("%-5s %-9s %-10s",
                        account.getAccountID(), account.getCurrencyCode(), account.getBalance()));
            }
        }

        System.out.println();

    }

    // Выводит в консоль строковое представление доступных в системе валют
    public void printCurrencies() {
        Map<String, Currency> currencyMap = currencyRepository.getAllCurrencies();
        for (Map.Entry<String, Currency> entry : currencyMap.entrySet()) {
            Currency currency = entry.getValue();
            System.out.println(currency.getCurrencyCode() + " - " + currency.getCurrencyName());
        }
    }


    // === UPDATE ===

    // Осуществляет перевод суммы денег с одного счёта (по ID) на другой (по ID)
    @Override
    public void transferMoney(int sourceAccountID, int targetAccountID, BigDecimal amount) {
        Account sourceAccount = getAccountByID(sourceAccountID);
        Account targetAccount = getAccountByID(targetAccountID);

        // Списание суммы с исходного счёта
        sourceAccount.setBalance(sourceAccount.getBalance().subtract(amount));

        // Перевод суммы через евро
        // 1. Сначала переводим сумму в евро (базовая валюта)
        BigDecimal amountInEUR = toEUR(sourceAccountID, amount);
        System.out.println(amountInEUR);

        // 2. Затем из евро переводим в валюту целевого счёта
        BigDecimal convertedAmount = toAccountCurrency(targetAccountID, amountInEUR);
        System.out.println(convertedAmount);

        // Зачисление суммы на целевой счёт
        targetAccount.setBalance(targetAccount.getBalance().add(convertedAmount));
    }

    // Осуществляет внесение денег на счёт
    @Override
    public void depositTransfer(int targetAccountID, BigDecimal amount) {
        // Получаем счёт по ID
        Account targetAccount = getAccountByID(targetAccountID);
        // Проверяем, что счёт существует
        if (targetAccount == null) {
            throw new IllegalArgumentException("Счёт по указанному ID не найден");
        }
        // Проверяем, что сумма положительная
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Сумма должна быть положительной");
        }
        // Вносим деньги на счёт
        targetAccount.setBalance(targetAccount.getBalance().add(amount));
    }

    // Осуществляет снятие денег со счёта
    @Override
    public void withdrawMoney(int sourceAccountID, BigDecimal amount) {
        // Получаем счёт по ID
        Account sourceAccount = getAccountByID(sourceAccountID);
        // Проверяем, что счёт существует
        if (sourceAccount == null) {
            throw new IllegalArgumentException("Счёт по указанному ID не найден");
        }
        // Проверяем, что сумма положительная
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Сумма должна быть положительной");
        }
        // Проверяем, что на счёте достаточно средств
        if (sourceAccount.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Недостаточно средств на счёте");
        }
        // Списываем деньги со счёта
        sourceAccount.setBalance(sourceAccount.getBalance().subtract(amount));
    }


    // Возвращает значение любой суммы в евро (в зависимости от валюты счёта по ID)
    private BigDecimal toEUR(int accountID, BigDecimal amount) {
        BigDecimal rateBD = BigDecimal.valueOf(getCurrencyRate(getCurrencyCode(accountID)));

        if (rateBD.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ArithmeticException("Курс должен быть больше нуля");
        }

        // Умножение для перевода в евро
        return amount.multiply(rateBD).setScale(2, RoundingMode.HALF_UP);

    }


    // Возвращает значение суммы в евро, переведённой в валюту счёта
    private BigDecimal toAccountCurrency(int accountID, BigDecimal amount) {
        BigDecimal rateBD = BigDecimal.valueOf(getCurrencyRate(getCurrencyCode(accountID)));

        if (rateBD.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ArithmeticException("Курс должен быть больше нуля");
        }

        // Делим для перевода из евро в целевую валюту
        return amount.divide(rateBD, 2, RoundingMode.HALF_UP);

    }

    // Осуществляет вход пользователя в систему и возвращает статус успеха операции
    @Override
    public boolean loginUser(String email, String password) {
        if (email == null || password == null) return false;

        User user = userRepository.getUserByEmail(email);
        if (user == null) {
            System.out.println("Адрес электронной почты введён неверно. Повторите ввод.");
            return false;
        }

        if (!user.getPassword().equals(password)) {
            System.out.println("Пароль введён неверно. Повторите ввод.");
            return false;
        }

        if (user.getRole() == UserRole.BLOCKED) {
            System.out.println("\nВход в систему невозможен. Ваша учётная запись заблокирована.");
            return false;
        }

        activeUser = user;
        return true;
    }

    // Осуществляет выход пользователя из системы
    @Override
    public void logout() {
        activeUser = null;
    }

}