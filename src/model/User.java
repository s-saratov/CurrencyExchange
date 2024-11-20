package model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author olgakharina
 * @date 15.11.24
 */
public class User {

    // Поля

    private final int userID;                   // идентификационный номер
    private String name;                        // имя
    private String email;                       // адрес электронной почты
    private String password;                    // пароль
    private UserRole role;                      // роль

    // Базовый конструктор
    public User(int userID, String name, String email, String password) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Конструктор User с параметром роли пользователя
    public User(int userID, String name, String email, String password, UserRole role) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        /* Если роль не была задана при создании пользователя - по умолчанию "USER" */
        this.role = role != null ? role : UserRole.USER;
    }

    // Геттеры и сеттеры

    public int getUserID() {
        return userID;
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

    // Возвращает строковое представление экземпляра класса (toString)
    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}