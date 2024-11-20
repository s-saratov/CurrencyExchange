package repository;

import model.User;
import model.UserRole;
import java.util.List;

public interface UserRepository {

    // CREATE
    void addAccountToUserAccounts(int userID, int accountId);
    User addUser(int userID, String name, String email, String password);

    // READ
    List<User> getUserByName(String name);
    boolean isEmailExists(String email);
    List<User> getUsersByRole(UserRole... roles); // Используем UserRole
    User getUserByEmail(String email);
    User getUserByID(int userID);

    // DELETE
    boolean deleteUser(int userID);
    void removeAccountFromUserAccounts(int userID, int accountId);
}
