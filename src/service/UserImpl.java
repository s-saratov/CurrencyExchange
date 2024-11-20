package service;

import model.User;
import model.UserRole;
import repository.UserRepository;
import utils.validator.UserValidator;

import java.util.List;
import java.util.Map;

/**
 * @author olgakharina
 * @date 20.11.24
 */
/**
 * Реализация интерфейса UserService для управления пользователями.
 * Предоставляет методы регистрации, авторизации, получения и удаления пользователей.
 */
public class UserImpl implements UserService {

    private final UserRepository userRepository; // Хранилище данных пользователей
    private User activeUser; // Текущий активный пользователь

    // Конструктор для инициализации репозитория
    public UserImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Регистрирует нового пользователя.
     *
     * @param name     Имя пользователя.
     * @param email    Электронная почта пользователя.
     * @param password Пароль пользователя.
     * @return Зарегистрированный пользователь или null, если регистрация не удалась.
     */
    @Override
    public User registerUser(String name, String email, String password) {
        try {
            // Валидация данных
            UserValidator.isUsernameValid(name);
            UserValidator.isEmailValid(email);
            UserValidator.isPasswordValid(password);

            // Проверка, существует ли email
            if (userRepository.isEmailExists(email)) {
                System.err.println("Пользователь с таким email уже существует.");
                return null;
            }

            // Создание и сохранение нового пользователя
            return userRepository.addUser(name, email, password);
        } catch (Exception e) {
            System.err.println("Ошибка регистрации пользователя: " + e.getMessage());
            return null;
        }
    }

    /**
     * Возвращает пользователя по ID.
     *
     * @param userID ID пользователя.
     * @return Найденный пользователь или null, если пользователь не найден.
     */
    @Override
    public User getUserByID(int userID) {
        return userRepository.getUserByID(userID);
    }

    /**
     * Возвращает список всех пользователей.
     *
     * @return Список пользователей.
     */
    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    /**
     * Возвращает активного пользователя, если он не заблокирован.
     *
     * @param userID ID пользователя.
     * @return Активный пользователь или null, если пользователь не найден или заблокирован.
     */
    @Override
    public User getActiveUser(int userID) {
        User user = userRepository.getUserByID(userID);
        if (user != null && user.getRole() != UserRole.BLOCKED) {
            return user;
        }
        return null; // Пользователь либо заблокирован, либо не найден
    }

    /**
     * Возвращает пользователей, соответствующих указанным ролям.
     *
     * @param roles Роли пользователей.
     * @return Карта пользователей, где ключ — ID, а значение — пользователь.
     */
    @Override
    public Map<Integer, User> getUsersByRole(UserRole... roles) {
        return userRepository.getUsersByRole(roles);
    }

    /**
     * Авторизует пользователя в системе.
     *
     * @param email    Электронная почта пользователя.
     * @param password Пароль пользователя.
     * @return true, если вход выполнен успешно; false, если авторизация не удалась.
     */
    @Override
    public boolean loginUser(String email, String password) {
        try {
            User user = userRepository.getUserByEmail(email);
            if (user != null && user.getPassword().equals(password)) {
                activeUser = user; // Устанавливаем активного пользователя
                System.out.println("Пользователь " + user.getName() + " успешно вошел в систему.");
                return true;
            }
            System.err.println("Ошибка авторизации: неверный email или пароль.");
            return false;
        } catch (Exception e) {
            System.err.println("Ошибка авторизации: " + e.getMessage());
            return false;
        }
    }

    /**
     * Завершает сеанс активного пользователя.
     */
    @Override
    public void logoutUser() {
        if (activeUser != null) {
            System.out.println("Пользователь " + activeUser.getName() + " вышел из системы.");
            activeUser = null;
        } else {
            System.err.println("Нет активного пользователя для завершения сеанса.");
        }
    }

    /**
     * Удаляет пользователя по ID.
     *
     * @param userID ID пользователя.
     * @return true, если пользователь успешно удалён; false в противном случае.
     */
    @Override
    public boolean deleteUserByID(int userID) {
        try {
            if (userRepository.deleteUser(userID)) {
                System.out.println("Пользователь с ID " + userID + " успешно удалён.");
                return true;
            }
            System.err.println("Ошибка удаления: пользователь с ID " + userID + " не найден.");
            return false;
        } catch (Exception e) {
            System.err.println("Ошибка удаления пользователя: " + e.getMessage());
            return false;
        }
    }
}