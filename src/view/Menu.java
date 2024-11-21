package view;

import model.User;
import model.UserRole;
import service.MainService;
import utils.Utils;
import utils.validator.EmailValidateException;
import utils.validator.LoginValidator;
import utils.validator.PasswordValidateException;

import java.math.BigDecimal;
import java.util.Scanner;

public class Menu {

    // Поля

    private final MainService service;
    private final Scanner scanner = new Scanner(System.in);

    // Конструктор

    public Menu(MainService service) {
        this.service = service;
    }

    // Методы

    // Запускает программу
    public void run() throws EmailValidateException {
        this.showMainMenu();
    }

    // Главное меню
    private void showMainMenu() throws EmailValidateException {

        System.out.println("\u001B[32m\nДобро пожаловать в обменный пункт валюты!\u001B[0m\n" +
                "1. Обмен валюты\n" +
                "2. Меню пользователя\n" +
                "3. Меню администратора\n" +
                "0. Выход из системы\n");

        int choice = getSelection();

        while (true) {
            switch (choice) {
                case 1:
                    showExchangeMenu();
                    break;
                case 2:
                    showUserMenu();
                    break;
                case 3:
                    if (service.getActiveUser() == null) {
                        System.out.print("\u001B[31m\nДоступ запрещён! Вы не авторизованы как администратором системы." +
                                "\u001B[0m\n");
                        waitRead();
                        showMainMenu();
                        break;
                    }
                    showAdminMenu();
                    break;
                case 0:
                    System.out.println("До свидания!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Некорректный выбор. Повторите ввод.");
                    choice = getSelection();
                    break;
            }
        }
    }

    private void showExchangeMenu() throws EmailValidateException {
        System.out.println("\nОбмен валюты:\n" +
                "1. Показать курсы валюты\n" +
                "2. Произвести обмен\n" +
                "0. Вернуться в предыдущее меню\n");

        int choice = getSelection();

        while (true) {
            switch (choice) {
                case 1:
                    service.printCurrencyRates();
                    System.out.println();
                    waitRead();
                    showExchangeMenu();
                    break;
                case 2:
                    if (service.getActiveUser() == null) {
                        System.out.println("\u001B[31m\nВы не авторизованы в системе. Пройдите авторизацию.\u001B[0m");
                        waitRead();
                        showExchangeMenu();
                        }
                    showExchangeTransactionMenu();
                    break;
                case 0:
                    showMainMenu();
                    break;
                default:
                    System.out.println("Некорректный выбор. Повторите ввод.");
                    choice = getSelection();
                    break;
            }
        }
    }

    private void showExchangeTransactionMenu() throws EmailValidateException {
        System.out.println("\nВведите ID счёта списания:\n");
        service.printUserAccounts();

        int sourceAccountID = getSelection();

        // TODO: написать проверку корректности ID

        System.out.println("\nВведите ID счёта-получателя:\n");

        service.printUserAccounts(sourceAccountID);

        int targetAccountID = getSelection();

        // TODO: написать проверку корректности ID

        BigDecimal amount = getAmount();

        service.transferMoney(sourceAccountID, targetAccountID, amount);
        service.printUserAccounts();
        waitRead();
        showExchangeMenu();
    }

    private void showUserMenu() throws EmailValidateException {
        System.out.println(
                "\nМеню пользователя:\n" +
                        "1. Вход в систему\n" +
                        "2. Регистрация нового пользователя\n" +
                        "3. Выход из системы\n" +
                        "4. Показать список счетов\n" + // (по названию)
                        "5. Добавить новый счёт\n" +
                        "6. Внести деньги на счёт\n" +
                        "7. Снять деньги со счёта\n" +
                        "0. Вернуться в предыдущее меню\n"
        );

        int choice = this.getSelection();

        while (true) {
            switch (choice) {
                // Вернуться в предыдущее меню
                case 0:
                    this.showMainMenu();
                    break;

                // Вход в систему
                case 1:
                    this.service.logout();
                    this.showLoginMenu();
                    break;

                // Регистрация нового пользователя
                case 2:
                    this.service.logout();
                    this.showAccountCreationMenu();
                    break;

                // Выход из системы
                case 3:
                    this.service.logout();
                    this.showMainMenu();
                    break;

                // Показать список счетов
                case 4:
                    if(service.getActiveUser() == null) {
                        System.out.println("\u001B[31m\nВы не авторизованы в системе. Пройдите авторизацию.\u001B[0m");
                        waitRead();
                        showUserMenu();
                        break;
                    }
                    System.out.println("\nCписок Ваших счетов:\n");

                    service.printUserAccounts();

                    this.waitRead();
                    this.showUserMenu();
                    break;

                 // Добавить новый счёт
                case 5:
                    if(service.getActiveUser() == null) {
                        System.out.println("\u001B[31m\nВы не авторизованы в системе. Пройдите авторизацию.\u001B[0m");
                        waitRead();
                        showUserMenu();
                        break;
                    }

                    String currencyCode = "EUR";
                    System.out.println("\nВыберете валюту счёта:");
                    service.printCurrencies();

                    System.out.print("\nВведите код валюты: ");
                    String string = scanner.nextLine();

                    if (service.isCurrencyCodeCorrect(string)) currencyCode = string;
                    else {
                        while (!service.isCurrencyCodeCorrect(string)) {
                            System.out.print("Введённый Вами код некорректен. Повторите ввод: ");
                            string = scanner.nextLine();
                        }
                    }
                    service.addAccount(service.getActiveUser().getUserID(), currencyCode, BigDecimal.valueOf(0.0));
                    System.out.println("\nНовый счёт в валюте " + currencyCode + " успешно создан.");

                    this.waitRead();
                    this.showUserMenu();
                    break;

                // Внести деньги на счёт
                case 6:
                    if(service.getActiveUser() == null) {
                        System.out.println("\u001B[31m\nВы не авторизованы в системе. Пройдите авторизацию.\u001B[0m");
                        waitRead();
                        showUserMenu();
                        break;
                    }

                    System.out.println("\nВведите ID счёта зачисления:\n");
                    service.printUserAccounts();

                    int targetAccountID = getSelection();

                    BigDecimal amount = getAmount();
                    service.depositTransfer(targetAccountID, amount);

                    System.out.printf("\nБаланс счёта № %d теперь составляет %s %s.\n",
                            targetAccountID,
                            service.getAccountByID(targetAccountID).getBalance(),
                            service.getAccountByID(targetAccountID).getCurrencyCode());

                    waitRead();
                    showUserMenu();

                // Снять деньги со счёта
                case 7:
                    if(service.getActiveUser() == null) {
                        System.out.println("\u001B[31m\nВы не авторизованы в системе. Пройдите авторизацию.\u001B[0m");
                        waitRead();
                        showUserMenu();
                        break;
                    }

                    System.out.println("\nВведите ID счёта снятия:\n");
                    service.printUserAccounts();

                    int sourceAccountID = getSelection();

                    amount = getAmount();
                    service.withdrawMoney(sourceAccountID, amount);

                    System.out.printf("\nБаланс счёта № %d теперь составляет %s %s.\n",
                            sourceAccountID,
                            service.getAccountByID(sourceAccountID).getBalance(),
                            service.getAccountByID(sourceAccountID).getCurrencyCode());

                    waitRead();
                    showUserMenu();


                default:
                    System.out.print("Введённое Вами число некорректно!");
                    choice = this.getSelection();
                    break;
            }
        }
    }

    private void showLoginMenu() throws EmailValidateException {

        String email = "test@test.com";
        String password = "Qwert123!";

        System.out.println("\nВход в систему.");
        System.out.print("Введите адрес электронной почты: ");
        String string = scanner.nextLine();

        boolean isValid = false;

        while (!isValid) {
            try {
                LoginValidator.isEmailValid(string);
                isValid = true;
                email = string;
            } catch (EmailValidateException e) {
                System.out.print("Введённый Вами адрес не соответствует требованиям. Повторите ввод: ");
                string = scanner.nextLine();
            }
        }

        System.out.print("Введите пароль: ");
        string = scanner.nextLine();

        isValid = false;

        while (!isValid) {
            try {
                LoginValidator.isPasswordValid(string);
                isValid = true;
                password = string;
            } catch (PasswordValidateException e) {
                System.out.print("Введённый Вами пароль не соответствует требованиям. Повторите ввод: ");
                string = scanner.nextLine();
            }
        }

        if (service.loginUser(email, password)) {
            showMainMenu();
        } else {
            showUserMenu();
        }
    }

    private void showAccountCreationMenu() throws EmailValidateException {

        String email = "test@test.com";
        String password = "Qwert123!";

        System.out.println("\nРегистрация нового пользователя.");
        System.out.print("Введите адрес имя: ");
        String name = scanner.nextLine();
        System.out.print("Введите адрес электронной почты: ");
        String string = scanner.nextLine();
        boolean isValid = false;

        while (!isValid) {
            try {
                LoginValidator.isEmailValid(string);
                isValid = true;
                email = string;
            } catch (EmailValidateException e) {
                System.out.print("Введённый Вами адрес не соответствует требованиям. Повторите ввод: ");
                string = scanner.nextLine();
            }
        }

        System.out.print("Введите пароль: ");
        string = scanner.nextLine();
        isValid = false;

        while (!isValid) {
            try {
                LoginValidator.isPasswordValid(string);
                isValid = true;
                password = string;
            } catch (PasswordValidateException e) {
                System.out.print("Введённый Вами пароль не соответствует требованиям. Повторите ввод: ");
                string = scanner.nextLine();
            }
        }

        User user = service.registerUser(name, email, password);
        if (user == null) {
            System.out.println("Регистрация не пройдена. Повторите попытку.");
            showAccountCreationMenu();
        } else {
            System.out.println("Регистрация успешно завершена. Теперь Вы можете войти в систему.");
            showUserMenu();
        }

    }

    private void showAdminMenu() throws EmailValidateException {
        if (this.service.getActiveUser().getRole() != UserRole.ADMIN) {

            System.out.print("\u001B[31m\nДоступ запрещён! Вы не являетесь администратором системы.\u001B[0m\n");
            this.waitRead();
            this.showMainMenu();
        }

        System.out.println(
                "\nМеню администратора\n" +
                        "1. Посмотреть список всех пользователей\n" +
                        "2. Заблокировать пользователя\n" +
                        "3. Разблокировать пользователя\n" +
                        "4. Добавить новую валюту\n" +
                        "0. Вернуться в предыдущее меню\n"
        );

        int choice = getSelection();

        while (true) {
            switch (choice) {

                // Вернуться в предыдущее меню
                case 0:
                    this.showMainMenu();
                    break;

                case 1:
                    System.out.println("\nПолный список пользователей системы:\n");
                    System.out.println(Utils.printUsers(service.getUsersByRole(UserRole.ADMIN, UserRole.USER, UserRole.BLOCKED)));
                    waitRead();
                    showAdminMenu();
                    break;

                // Заблокировать пользователя
                case 2:
                    System.out.println("\nВыберете пользователя для блокировки по ID:\n");
                    System.out.println(Utils.printUsers(service.getUsersByRole(UserRole.ADMIN, UserRole.USER)));

                    int id = getSelection();

                    while (this.service.getUserByID(id) == null) {
                        System.out.print("Введённый Вами ID некорректен. ");
                        id = getSelection();
                    }

                    this.service.getUserByID(id).setRole(UserRole.BLOCKED);
                    System.out.printf("\nПользователь %s заблокирован.\n", this.service.getUserByID(id).getEmail());
                    this.waitRead();
                    this.showAdminMenu();
                    break;

                // Разблокировать пользователя
                case 3:
                    System.out.println("\nВыберете пользователя для разблокирования по ID:\n");
                    System.out.println(Utils.printUsers(service.getUsersByRole(UserRole.BLOCKED)));

                    id = getSelection();

                    while (this.service.getUserByID(id) == null) {
                        System.out.print("Введённый Вами ID некорректен. ");
                        id = getSelection();
                    }

                    this.service.getUserByID(id).setRole(UserRole.USER);
                    System.out.printf("\nПользователь %s разблокирован.\n", this.service.getUserByID(id).getEmail());
                    waitRead();
                    showAdminMenu();
                    break;

                // Добавить новую валюту
                case 4:

                    System.out.println("\nДобавление новой валюты.");

                    System.out.print("Введите название валюты: ");
                    String currencyName = this.scanner.nextLine();

                    System.out.print("Введите код валюты: ");
                    String currencyCode = this.scanner.nextLine().toUpperCase();

                    System.out.print("Введите курс евро к единицы валюты к евро: ");
                    double rate = this.scanner.nextDouble();

                    service.addCurrency(currencyCode, currencyName, rate);
                    System.out.printf("\nВалюта с кодом %s успешно добавлена.\n", currencyCode);
                    waitRead();
                    showAdminMenu();
                    break;

                default:
                    System.out.print("Упс! Неправильное значение, попробуйте еще раз.");
                    choice = this.getSelection();
                    break;
            }

        }
    }

    private void waitRead() {
        System.out.print("Для продолжения нажмите Enter... ");
        scanner.nextLine();
    }

    /**
     * Возвращает только введённое число (предотвращая ошибку в случае ввода символов).
     *
     * @return Выбор.
     */
    private int getSelection() {
        int selection;
        while (true) {
            System.out.print("Введите Ваш выбор: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Вы ввели не число. Повторите ввод.");
                scanner.nextLine();
            } else {
                selection = scanner.nextInt();
                scanner.nextLine();
                return selection;
            }
        }
    }

    private BigDecimal getAmount() {
        BigDecimal amount;
        while (true) {
            System.out.print("Введите сумму: ");
            if (!scanner.hasNextBigDecimal()) {
                System.out.println("Вы ввели не число. Повторите ввод.");
                scanner.nextLine();
            } else {
                amount = scanner.nextBigDecimal();
                scanner.nextLine();
                return amount;
            }
        }
    }

}
