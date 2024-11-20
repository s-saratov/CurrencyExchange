package main;

import model.UserRole;
import repository.UserRepositoryImpl;

public class Main {
    public static void main(String[] args) {
        // Создание репозитория пользователей
        UserRepositoryImpl userRepository = new UserRepositoryImpl();

        // Добавление новых пользователей
        userRepository.addUser("Charlie", "charlie@example.com", "password3", UserRole.USER);
        userRepository.addUser("Diana", "diana@example.com", "password4", UserRole.BLOCKED);

        // Вывод всех пользователей
        System.out.println("\nСписок пользователей:");
        userRepository.printUsers();

        // Чтение и вывод истории транзакций
        System.out.println("\nЧтение из лог-файла:");
        userRepository.readTransactionLog();
    }
}