package model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author olgakharina
 * @date 15.11.24
 */

import java.util.Objects;

public class User {

    //Поля
    private final int userID;                   //номер пользователя
    private String name;                        //Имя пользователя
    private String email;                       //Адрес электронной почты
    private String password;                    //Пароль пользователя
    private UserRole role;                      //Роль пользователя

    //Конструктор без роли
    public User(int userID, String name, String email, String password) {
        // По умолчанию роль USER
        this(userID, name, email, password, UserRole.USER);
    }

    //Конструктор с ролью
    public User(int userID, String name, String email, String password, UserRole role) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.password = password;
        //Роль по умолчанию USER
        this.role = role != null ? role : UserRole.USER;
    }

    //Геттеры
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

    // Сеттеры
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

    //Метод для проверки равенства пользователей по userID (переопределение equals)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userID == user.userID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID);
    }

    //Метод для красивого отображения информации о пользователе
    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}
