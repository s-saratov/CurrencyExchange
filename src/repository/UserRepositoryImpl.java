package repository;

import model.User;
import model.UserRole;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;



public class UserRepositoryImpl {

    private final Map<Integer, User> users = new HashMap<>(); // Карта пользователей
    private final AtomicInteger currentID = new AtomicInteger(1); // Генерация уникальных ID
    private static final String LOG_FILE = "log.txt"; // Имя файла для записи

    // Конструктор с предустановленными пользователями
    public UserRepositoryImpl() {
        User user1 = new User(currentID.getAndIncrement(), "Alice", "alice@example.com", "password1", UserRole.USER);
        User user2 = new User(currentID.getAndIncrement(), "Bob", "bob@example.com", "password2", UserRole.ADMIN);

        users.put(user1.getUserID(), user1);
        users.put(user2.getUserID(), user2);

        writeTransactionLog("Инициализация пользователей: " + users);
    }

    // Метод для добавления пользователя
    public void addUser(String name, String email, String password, UserRole role) {
        int id = currentID.getAndIncrement();
        User newUser = new User(id, name, email, password, role);
        users.put(id, newUser);
        writeTransactionLog("Добавлен пользователь: " + newUser);
    }

    // Метод для записи в лог-файл
    private void writeTransactionLog(String transaction) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write(transaction);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Ошибка записи в лог-файл: " + e.getMessage());
        }
    }

    // Метод для чтения из лог-файла
    public void readTransactionLog() {
        System.out.println("История транзакций:");
        try (BufferedReader reader = new BufferedReader(new FileReader(LOG_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Ошибка чтения лог-файла: " + e.getMessage());
        }
    }

    // Метод для отображения всех пользователей
    public void printUsers() {
        users.forEach((id, user) -> System.out.println("ID: " + id + ", User: " + user));
    }
}
