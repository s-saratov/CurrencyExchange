package utils;

import model.User;

import java.util.List;

public class Utils {


    // Возвращает список пользователей в табличном формате
    public static String printUsers(List<User> users) {
        String result = String.format("\u001B[33m%-5s %-30s %-30s %-15s\u001B[0m\n", "ID:", "Email:", "Password:", "Role:");
        for (User user : users) {
            result = result.concat(String.format("%-5d %-30s %-30s %-15s\n",
                    user.getUserID(), user.getEmail(), user.getPassword(), user.getRole()));
        }
        return result;
    }

}
