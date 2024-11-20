package repository;

import model.User;
import model.UserRole;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public User registerUser(String name, String email, String password, UserRole role) {
        //проверка, существует ли пользователь с таким email
        if (isEmailExists(email)) {
            System.err.println("Пользователь с email " + email + " уже существует.");
            return null; //возвращаем null, если email уже занят
        }

        //генерируем уникальный ID
        int userId = currentID.getAndIncrement();
        //создаем нового пользователя
        User newUser = new User(userId, name, email, password, role);
        //добавляем пользователя в карту
        users.put(userId, newUser);
        writeTransactionLog("Зарегистрирован новый пользователь: " + newUser);

        //возвращаем зарегистрированного пользователя
        return newUser;
    }

    //карта для отслеживания, кто авторизован
    private final Map<Integer, Boolean> userSessions = new HashMap<>();

    //логин пользователя
    public boolean loginUser(int userId, String password) {
        User user = users.get(userId);
        if (user != null && user.getPassword().equals(password)) {
            userSessions.put(userId, true); // Авторизуем пользователя
            writeTransactionLog("Пользователь с ID " + userId + " вошел в систему.");
            return true;
        }
        System.err.println("Неверный ID пользователя или пароль.");
        return false;
    }

    //Logout пользователя
    public boolean logoutUser(int userId) {
        if (userSessions.containsKey(userId) && userSessions.get(userId)) {
            //Завершаем сессию пользователя
            userSessions.put(userId, false);
            writeTransactionLog("Пользователь с ID " + userId + " вышел из системы.");
            return true;
        }
        System.err.println("Пользователь с ID " + userId + "не авторизован.");
        return false;
    }



    //карта пользователей
    private final Map<Integer, User> users = new HashMap<>();
    //генерация уникальных ID
    private final AtomicInteger currentID = new AtomicInteger(1);
    //Имя файла для логирования
    private static final String LOG_FILE = "log.txt";

    //Конструктор с предустановленными пользователями
    public UserRepositoryImpl() {
        User user1 = new User(currentID.getAndIncrement(), "Alex", "alexe@example.com", "password1", UserRole.USER);
        User user2 = new User(currentID.getAndIncrement(), "Bogdan", "bogdan@example.com", "password2", UserRole.ADMIN);

        users.put(user1.getUserID(), user1);
        users.put(user2.getUserID(), user2);

        writeTransactionLog("Инициализация пользователей: " + users);
    }

    @Override
    //реализация добавления аккаунта пользователю
    public void addAccountToUserAccounts(int userId, int accountId) {

    }

    @Override

    public User addUser(int userId, String name, String email, String password) {
        User newUser = new User(userId, name, email, password);
        users.put(userId, newUser);
        writeTransactionLog("Добавлен пользователь: " + newUser);
        return newUser;
    }

    @Override
    public List<User> getUserByName(String name) {

        return users.values().stream()
                .filter(user -> user.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isEmailExists(String email) {

        return users.values().stream()
                .anyMatch(user -> user.getEmail().equalsIgnoreCase(email));
    }

    @Override
    public List<User> getUsersByRole(UserRole... roles) {

        Set<UserRole> roleSet = new HashSet<>(Arrays.asList(roles));
        return users.values().stream()
                .filter(user -> roleSet.contains(user.getRole()))
                .collect(Collectors.toList());
    }

    @Override
    public User getUserByEmail(String email) {
        return users.values().stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public User getUserByID(int userId) {
        return users.get(userId);
    }

    @Override
    public boolean deleteUser(int userId) {

        if (users.containsKey(userId)) {
            users.remove(userId);
            writeTransactionLog("Удалён пользователь с ID: " + userId);
            return true;
        }
        return false;
    }

    @Override
    //реализация удаления аккаунта у пользователя
    public void removeAccountFromUserAccounts(int userId, int accountId) {

    }

    //логирование операций
    private void writeTransactionLog(String transaction) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write(transaction);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Ошибка записи в лог-файл: " + e.getMessage());
        }
    }

    public void readTransactionLog() {
        try (BufferedReader reader = new BufferedReader(new FileReader(LOG_FILE))) {
            reader.lines().forEach(System.out::println);
        } catch (IOException e) {
            System.err.println("Ошибка чтения лог-файла: " + e.getMessage());
        }
    }
}

