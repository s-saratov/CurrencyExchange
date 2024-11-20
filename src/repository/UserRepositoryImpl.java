package repository;
/**
 * @author olgakharina
 * @date 20.11.24
 */
import model.User;
import model.UserRole;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Реализация интерфейса {@link UserRepository} для управления пользователями.
 * Включает операции добавления, удаления, поиска пользователей и управление их сессиями.
 */
public class UserRepositoryImpl implements UserRepository {

    // Карта пользователей: ключ - ID пользователя, значение - объект User
    private final Map<Integer, User> users = new HashMap<>();
    // Генератор уникальных ID для пользователей
    private final AtomicInteger currentID = new AtomicInteger(1);
    // Имя файла для логирования операций
    private static final String LOG_FILE = "log.txt";
    // Карта для отслеживания сессий пользователей: ключ - ID пользователя, значение - авторизован ли пользователь
    private final Map<Integer, Boolean> userSessions = new HashMap<>();

    /**
     * Конструктор с предустановленными пользователями.
     * Добавляет двух пользователей в систему с фиксированными данными.
     */
    public UserRepositoryImpl() {
        User user1 = new User(currentID.getAndIncrement(), "Alex", "alexe@example.com", "password1", UserRole.USER);
        User user2 = new User(currentID.getAndIncrement(), "Bogdan", "bogdan@example.com", "password2", UserRole.ADMIN);

        // Добавляем пользователей в хранилище
        users.put(user1.getUserID(), user1);
        users.put(user2.getUserID(), user2);

        // Логируем инициализацию
        writeTransactionLog("Инициализация пользователей: " + users);
    }

    /**
     * Добавляет нового пользователя в систему.
     *
     * @param name     Имя пользователя.
     * @param email    Электронная почта пользователя.
     * @param password Пароль пользователя.
     * @return Объект {@link User}, представляющий добавленного пользователя.
     */
    @Override
    public User addUser(String name, String email, String password) {
        // Генерация уникального ID
        int userID = currentID.getAndIncrement();

        // Создание нового пользователя
        User newUser = new User(userID, name, email, password);

        // Добавление пользователя в карту
        users.put(userID, newUser);

        // Логирование операции
        writeTransactionLog("Добавлен пользователь: " + newUser);

        return newUser;
    }

    /**
     * Добавляет аккаунт пользователю. Пока метод не реализован.
     *
     * @param userId    ID пользователя.
     * @param accountId ID аккаунта, который нужно добавить.
     */
    @Override
    public void addAccountToUserAccounts(int userId, int accountId) {
        // TODO: Реализовать метод добавления аккаунта пользователю.
    }

    /**
     * Возвращает список пользователей с указанным именем.
     *
     * @param name Имя пользователя.
     * @return Список пользователей, соответствующих имени.
     */
    @Override
    public List<User> getUserByName(String name) {
        return users.values().stream()
                .filter(user -> user.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    /**
     * Проверяет, существует ли пользователь с указанным email.
     *
     * @param email Электронная почта для проверки.
     * @return true, если пользователь с таким email существует, иначе false.
     */
    @Override
    public boolean isEmailExists(String email) {
        return users.values().stream()
                .anyMatch(user -> user.getEmail().equalsIgnoreCase(email));
    }

    /**
     * Возвращает список пользователей, соответствующих указанным ролям.
     *
     * @param roles Роли для фильтрации.
     * @return Список пользователей, соответствующих указанным ролям.
     */
    @Override
    public Map<Integer, User> getUsersByRole(UserRole... roles) {
        Set<UserRole> roleSet = new HashSet<>(Arrays.asList(roles));
        return (Map<Integer, User>) users.values().stream()
                .filter(user -> roleSet.contains(user.getRole()))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getAllUsers() {
        // Возвращаем список значений (объектов User) из карты users
        return new ArrayList<>(users.values());
    }

    /**
     * Возвращает пользователя по указанному email.
     *
     * @param email Электронная почта пользователя.
     * @return Объект {@link User}, если пользователь найден, иначе null.
     */
    @Override
    public User getUserByEmail(String email) {
        return users.values().stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    /**
     * Возвращает пользователя по его уникальному ID.
     *
     * @param userId Уникальный идентификатор пользователя.
     * @return Объект {@link User}, если пользователь найден, иначе null.
     */
    @Override
    public User getUserByID(int userId) {
        return users.get(userId);
    }

    /**
     * Удаляет пользователя из системы.
     *
     * @param userId Уникальный идентификатор пользователя.
     * @return true, если пользователь был удален, иначе false.
     */
    @Override
    public boolean deleteUser(int userId) {
        if (users.containsKey(userId)) {
            users.remove(userId);
            writeTransactionLog("Удалён пользователь с ID: " + userId);
            return true;
        }
        return false;
    }

    /**
     * Удаляет аккаунт у пользователя. Пока метод не реализован.
     *
     * @param userId    Уникальный идентификатор пользователя.
     * @param accountId Уникальный идентификатор аккаунта, который нужно удалить.
     */
    @Override
    public void removeAccountFromUserAccounts(int userId, int accountId) {
        // TODO: Реализовать метод удаления аккаунта пользователя.
    }

    /**
     * Записывает транзакции или операции в лог-файл.
     *
     * @param transaction Сообщение для записи в лог.
     */
    private void writeTransactionLog(String transaction) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write(transaction);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Ошибка записи в лог-файл: " + e.getMessage());
        }
    }

    /**
     * Читает записи из лог-файла и выводит их в консоль.
     */
    public void readTransactionLog() {
        try (BufferedReader reader = new BufferedReader(new FileReader(LOG_FILE))) {
            reader.lines().forEach(System.out::println);
        } catch (IOException e) {
            System.err.println("Ошибка чтения лог-файла: " + e.getMessage());
        }
    }
}