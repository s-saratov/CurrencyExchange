package model;

public class User {

    // Поля

    private final int userID;                   // идентификационный номер
    private String name;                        // имя
    private String email;                       // адрес электронной почты
    private String password;                    // пароль
    private UserRole role;                      // роль

    // Конструкторы

    // Базовый конструктор
    public User(int userID, String name, String email, String password) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = UserRole.USER;
    }

    // Конструктор с параметром роли пользователя
    public User(int userID, String name, String email, String password, UserRole role) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Геттеры и сеттеры


    public int getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
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