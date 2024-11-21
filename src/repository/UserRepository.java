package repository;

import model.User;
import model.UserRole;

import java.util.List;

public interface UserRepository {

    // === CREATE ===

    // Добавляет счёт в список пользователя TODO: нужен ли этот метод???
    // void addAccountToUserAccounts(int userId, int accountId);

    // Добавляет пользователя в карту пользователей (без указания роли)
    User addUser(String name, String email, String password);

    // Добавляет пользователя в карту пользователей (с указанием роли)
    User addUser(String name, String email, String password, UserRole role);

    // === READ ===

    // Проверяет, зарегистрирован ли пользователь с указанным именем
    List<User> getUserByName(String name);

    // Проверяет, зарегистрирован ли пользователь с заданным адресом электронной почты
    boolean isEmailExists(String email);

    // Возвращает список пользователей по заданным ролям

    List<User> getUsersByRole(UserRole... roles);

    // Возвращает объект пользователя по адресу электронной почты
    User getUserByEmail(String email);

    // Возвращает объект пользователя по ID
    User getUserByID(int userId);

    // === UPDATE ===



    // === DELETE ===

    // Удаляет счёт пользователя из карты
    boolean deleteUser(int userId);

    // Добавляет счёт в список пользователя TODO: нужен ли этот метод???
    // void removeAccountFromUserAccounts(int userId, int accountId);

}