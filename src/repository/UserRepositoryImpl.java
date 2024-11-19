package repository;


import model.User;
import model.UserRole;
import model.Account;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class UserRepositoryImpl implements UserRepository {

    //Храним пользователей
    private final Map<Integer, User> users = new HashMap<>();
    //Храним счета пользователей
    private final Map<Integer, List<Account>> userAccounts = new HashMap<>();

    //Генерация уникальных ID
    private final AtomicInteger currentID = new AtomicInteger(1);

    //Конструктор с первичными данными
    public UserRepositoryImpl() {

        //Добавляем первичного пользователя
        User user1 = new User(currentID.getAndIncrement(), "Alex", "alexe@example.com", "password123", UserRole.USER);
        User user2 = new User(currentID.getAndIncrement(), "Bogdan", "bogdan@example.com", "password456", UserRole.ADMIN);
        User user3 = new User(currentID.getAndIncrement(), "Andrey", "andrey@example.com", "password789", UserRole.BLOCKED);

        //Добавляем пользователей в карту
        users.put(user1.getUserID(), user1);
        users.put(user2.getUserID(), user2);
        users.put(user3.getUserID(), user3);

        //Добавляем набор счетов для каждого пользователя
        userAccounts.put(user1.getUserID(), Arrays.asList(new Account(101), new Account(102)));
        userAccounts.put(user2.getUserID(), Arrays.asList(new Account(201), new Account(202)));
        userAccounts.put(user3.getUserID(), Arrays.asList(new Account(301), new Account(302)));
    }

    //Остальная реализация интерфейса UserRepository

    @Override
    public void addAccountToUserAccounts(int userId, int accountId) {

        userAccounts.computeIfAbsent(userId, k -> new ArrayList<>()).add(new Account(accountId));
    }

    @Override
    public User addUser(int userId, String name, String email, String password) {

        User newUser = new User(userId, name, email, password, UserRole.USER);
        users.put(userId, newUser);
        userAccounts.put(userId, new ArrayList<>());
        return newUser;

    }

    @Override
    public List<User> getUserByName(String name) {

        List<User> result = new ArrayList<>();
        for (User user : users.values()) {
            if (user.getName().equalsIgnoreCase(name)) {
                result.add(user);
            }
        }
        return result;
    }

    @Override
    public boolean isEmailExists(String email) {
        return users.values().stream().anyMatch(user -> user.getEmail().equalsIgnoreCase(email));
    }

    @Override
    public List<User> getUsersByRole(UserRole... roles) {

        List<User> result = new ArrayList<>();
        Set<UserRole> roleSet = new HashSet<>(Arrays.asList(roles));
        for (User user : users.values()) {
            if (roleSet.contains(user.getRole())) {
                result.add(user);
            }
        }
        return result;
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
            userAccounts.remove(userId);
            return true;
        }
        return false;
    }

    @Override
    public void removeAccountFromUserAccounts(int userId, int accountId) {

        List<Account> accounts = userAccounts.get(userId);
        if (accounts != null) {
            accounts.removeIf(account -> account.getAccountId() == accountId);
        }
    }
}

