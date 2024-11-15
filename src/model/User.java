package model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author olgakharina
 * @date 15.11.24
 */
public class User {
    // Идентификационный номер пользователя
    private final int userId;

    // Имя пользователя
    private String name;

    // Адрес электронной почты
    private String email;

    // Пароль
    private String password;

    // Роль пользователя
    private UserRole role;

    // Счета пользователя
    private Map<String, Account> userAccounts;

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
        this.userAccounts = new HashMap<>();
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

    public Map<String, Account> getUserAccounts() {
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
}
