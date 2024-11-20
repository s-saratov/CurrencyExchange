package repository;

import model.User;
import model.UserRole;

import java.util.List;

public interface UserRepository {



    User registerUser(String name, String email, String password, UserRole role);

    boolean logoutUser(int userId);
    boolean loginUser(int userId, String password);



    //добавление аккаунта пользователю
    void addAccountToUserAccounts(int userId, int accountId);

    //добавление нового пользователя
    User addUser(int userId, String name, String email, String password);

    //Получение списка пользователей по имени
    List<User> getUserByName(String name);

    //Проверка существования email
    boolean isEmailExists(String email);

    //Получение списка пользователей по ролям
    List<User> getUsersByRole(UserRole... roles);

    //Получение пользователя по email
    User getUserByEmail(String email);

    //Получение пользователя по ID
    User getUserByID(int userId);

    //Удаление пользователя
    boolean deleteUser(int userId);

    //Удаление аккаунта у пользователя
    void removeAccountFromUserAccounts(int userId, int accountId);
}



//    // Проверяет равенство двух счетов, возвращает статус
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) return true;                  // Сравнение с самим собой
//        if (obj == null || getClass() != obj.getClass()) return false; // Проверка на null и класс
//        Account account = (Account) obj;
//        return accountId == account.accountId &&
//                Objects.equals(owner, account.owner) &&
//                currency == account.currency &&
//                Objects.equals(balance, account.balance) &&
//                Objects.equals(creationDate, account.creationDate) &&
//                status == account.status;
//    }
//
//    // Возвращает хэш-код
//    @Override
//    public int hashCode() {
//        return Objects.hash(accountId, owner, currency, balance, creationDate, status);
//    }
//}


