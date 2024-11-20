package repository;
/**
 * @author olgakharina
 * @date 20.11.24
 */
import model.User;
import model.UserRole;

import java.util.List;
import java.util.Map;

/**
 * Интерфейс для работы с данными пользователей.
 * Определяет основные операции CRUD и дополнительные функции, связанные с пользователями и их аккаунтами.
 */
public interface UserRepository {
    /**
     * Добавляет нового пользователя в систему.
     *
     * @param name     Имя пользователя.
     * @param email    Электронная почта пользователя.
     * @param password Пароль пользователя.
     * @return Объект класса {@link User}, представляющий добавленного пользователя.
     */
    User addUser(String name, String email, String password);

    /**
     * Проверяет, существует ли пользователь с указанным email.
     *
     * @param email Электронная почта для проверки.
     * @return true, если пользователь с таким email существует, иначе false.
     */
    boolean isEmailExists(String email);

    /**
     * Возвращает список пользователей с указанным именем.
     *
     * @param name Имя пользователя.
     * @return Список объектов {@link User}, соответствующих заданному имени.
     */
    List<User> getUserByName(String name);

    /**
     * Возвращает список пользователей, соответствующих указанным ролям.
     *
     * @param roles Роли, по которым осуществляется фильтрация.
     * @return Список объектов {@link User}, соответствующих указанным ролям.
     */
    public Map<Integer, User> getUsersByRole(UserRole... roles);
    /**
     * Получает пользователя по его email.
     *
     * @return Объект {@link User}, если пользователь найден, иначе null.
     */

     List<User> getAllUsers();
;
    User getUserByEmail(String email);

    /**
     * Добавляет аккаунт пользователю.
     *
     * @param userID    Идентификатор пользователя.
     * @param accountId Идентификатор аккаунта.
     */
    void addAccountToUserAccounts(int userID, int accountId);

    /**
     * Получает пользователя по его ID.
     *
     * @param userID Уникальный идентификатор пользователя.
     * @return Объект {@link User}, если пользователь найден, иначе null.
     */
    User getUserByID(int userID);

    /**
     * Удаляет пользователя из системы.
     *
     * @param userID Уникальный идентификатор пользователя.
     * @return true, если пользователь успешно удален, иначе false.
     */
    boolean deleteUser(int userID);

    /**
     * Удаляет аккаунт у пользователя.
     *
     * @param userID    Уникальный идентификатор пользователя.
     * @param accountId Идентификатор аккаунта, который нужно удалить.
     */
    void removeAccountFromUserAccounts(int userID, int accountId);

}


