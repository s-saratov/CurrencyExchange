package service;

import model.User;
import model.UserRole;

import java.util.List;
import java.util.Map;

/**
 * @author olgakharina
 * @date 20.11.24
 */
public interface UserService {

    // Регистрирует пользователя на основании переданных адреса электронной почты, пароля и имени, возвращает экземпляр класса
    User registerUser(String name, String email, String password);

    // Возвращает пользователя по ID
    User getUserByID(int userID);
    // Возвращает карту всех пользователя
    List<User> getAllUsers();

    // Возвращает активного пользователя
    User getActiveUser(int userID);

    // Возвращает карту пользователей по ролям
    public Map<Integer, User> getUsersByRole(UserRole... roles);

    //Осуществляет вход пользователя в систему и возвращает статус успеха операции
    boolean loginUser(String email, String password);

    // Осуществляет выход пользователя из системы
    void logoutUser();

    // Удаляет юзера по id
    // Нужно добавить проверку, что пользователь удаляется вместе со всеми его счетами.
    boolean deleteUserByID(int userID);

}
