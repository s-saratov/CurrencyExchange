package repository;
/*
import model.User;

public interface UserRepository {

import model.Account;
import model.User;

import javax.management.relation.Role;
import java.util.List;
import java.util.Objects;
import java.util.Map;
import model.User;
import model.UserRole;

    public interface UserRepository {


        void addAccountToUserAccounts(int userId, int accountId);
        User addUser(int userId, String name, String email, String password);


        List<User> getUserByName(String name);
        boolean isEmailExists(String email);

        //Используем UserRole
        List<User> getUsersByRole(UserRole... roles);
        User getUserByEmail(String email);
        User getUserByID(int userId);

        //Удаление
        boolean deleteUser(int userId);
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


