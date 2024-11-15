package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author olgakharina
 * @date 15.11.24
 */
public class User {

    // Поля

    private final int userId;                   // идентификационный номер
    private String name;                        // имя
    private String email;                       // адрес электронной почты
    private String password;                    // пароль
    private UserRole role;                      // роль
    private List<Account> userAccounts;         // список счетов

    // Базовый конструктор
    public User(int userId, String name, String email, String password) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Конструктор User с параметром роли пользователя
    public User(int userIdd, String name, String email, String password, UserRole role) {
        this.userId = userIdd;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        /* Если роль не была задана при создании пользователя - по умолчанию "USER" */
        this.role = role != null ? role : UserRole.USER;
        // Инициализация списка счетов у пользователя
        this.userAccounts = new ArrayList<>();
    }

    // Геттеры и сеттеры

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }

    public List<Account> getUserAccounts() {
        return userAccounts;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    // Методы

    // Добавляет счёт в список пользователя, возвращает статус операции
    public boolean addAccountToUserAccounts(Account account) {
        return userAccounts.add(account);
    }

    // Удаляет счёт из списка пользователя, возвращает статус операции
    public boolean removeAccountFromUserAccounts (Account account) {
        return userAccounts.remove(account);
    }

    // Возвращает строковое представление экземпляра класса (toString)
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", userAccounts=" + userAccounts +
                '}';
    }
}