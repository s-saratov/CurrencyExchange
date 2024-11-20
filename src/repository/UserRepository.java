package repository;

import model.User;
import model.UserRole;

import java.util.List;

public interface UserRepository {



    User registerUser(String name, String email, String password, UserRole role);

    boolean logoutUser(int userID);
    boolean loginUser(int userID, String password);



    //добавление аккаунта пользователю
    void addAccountToUserAccounts(int userID, int accountId);

    //добавление нового пользователя
    User addUser(int userID, String name, String email, String password);

    //Получение списка пользователей по имени
    List<User> getUserByName(String name);

    //Проверка существования email
    boolean isEmailExists(String email);

    //Получение списка пользователей по ролям
    List<User> getUsersByRole(UserRole... roles);

    //Получение пользователя по email
    User getUserByEmail(String email);

    //Получение пользователя по ID
    User getUserByID(int userID);

    //Удаление пользователя
    boolean deleteUser(int userID);

    //Удаление аккаунта у пользователя
    void removeAccountFromUserAccounts(int userID, int accountId);
}


