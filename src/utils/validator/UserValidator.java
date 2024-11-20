package utils.validator;

/**
 * @author olgakharina
 * @date 20.11.24
 */
/**
 * Класс для валидации данных пользователя.
 */
public class UserValidator {

    /**
     * Метод для проверки валидности имени пользователя.
     *
     * @param username Имя пользователя.
     * @throws UsernameValidateException Если имя пользователя не соответствует требованиям.
     */
    public static void isUsernameValid(String username) throws utils.UsernameValidateException {
        if (username == null || username.isEmpty()) {
            throw new utils.UsernameValidateException("Имя пользователя не может быть пустым.");
        }

        // Проверяем длину имени пользователя
        if (username.length() < 3 || username.length() > 20) {
            throw new utils.UsernameValidateException("Имя пользователя должно содержать от 3 до 20 символов.");
        }

        // Проверяем, что имя пользователя содержит только допустимые символы
        for (char ch : username.toCharArray()) {
            if (!(Character.isAlphabetic(ch) || Character.isDigit(ch) || ch == '_' || ch == '-')) {
                throw new utils.UsernameValidateException("Имя пользователя может содержать только буквы, цифры, символы '_' или '-'.");
            }
        }

        // Проверяем, что имя пользователя начинается с буквы
        if (!Character.isAlphabetic(username.charAt(0))) {
            throw new utils.UsernameValidateException("Имя пользователя должно начинаться с буквы.");
        }

        // Проверка успешна
    }

    public static void isEmailValid(String email) throws utils.EmailValidateException {

        // 1. Должна присутствовать @
        int indexAt = email.indexOf('@');
        // int lastAt = email.lastIndexOf('@');
        if (indexAt == -1 || indexAt != email.lastIndexOf('@')) throw new utils.EmailValidateException("@ error");

        // 2. Точка после собаки
        int dotIndexAfterAt = email.indexOf('.', indexAt + 1);
        if (dotIndexAfterAt == -1) throw new utils.EmailValidateException(". after @ error");


        // 3. После последней точки есть 2 или более символов
        // test@fazx.com.ne.t
        int lastDotIndex = email.lastIndexOf('.');
        if (lastDotIndex + 2 >= email.length()) throw new utils.EmailValidateException("last . error");

        // 4.  Алфавит, цифры, '-', '_', '.', '@'
        /*
        Я беру каждый символ. Проверяю, что он не является "запрещенным"
        И если нахожу не подходящий символ - возвращаю false
         */
        for (int i = 0; i < email.length(); i++) {
            char ch = email.charAt(i);

            // Если символ удовлетворяет одному из условий на "правильность"
            boolean isPass = (Character.isAlphabetic(ch) ||
                    Character.isDigit(ch) ||
                    ch == '-' ||
                    ch == '_' ||
                    ch == '.' ||
                    ch == '@');

            // Если любой символ НЕ подходящий, сразу возвращаем false
            if (!isPass) throw new utils.EmailValidateException("illegal symbol");

            /*
            Равнозначные выражения.
            if (!Character.isAlphabetic(ch) && !Character.isDigit(ch) && ch != '-' && ch != '_' && ch != '.' && ch != '@') return false;
            if (!(Character.isAlphabetic(ch) || Character.isDigit(ch) || ch == '-' || ch == '_' || ch == '.' || ch == '@')) return false;
             */
        }

        // 5. До собаки должен быть хотя бы 1 символ == собака не первая в строке. Т.е. ее индекс не равен 0
        if (indexAt == 0) throw new utils.EmailValidateException("@ should not first");

        // 6. Первый символ - должна быть буква
        // Если 0-й символ НЕ является буквой, то email не подходит = return false;
        char firstChar = email.charAt(0);
        if (!Character.isAlphabetic(firstChar)) throw new utils.EmailValidateException("first symbol should be alphabetic");


        // Все проверки пройдены. email подходит.
    }

    public static void isPasswordValid(String password)  throws utils.PasswordValidateException {

        if (password == null || password.length() < 8) {
            throw new utils.PasswordValidateException("Password should be at least 8 characters");
        }

        boolean isDigit = false;
        boolean isUpperCase = false;
        boolean isLowerCase = false;
        boolean isSpecialSymbol = false;



        String symbols = "!%$@&*()[].,-";

        // Перебираю символы
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);

            if (Character.isDigit(ch)) isDigit = true;
            if (Character.isUpperCase(ch)) isUpperCase = true;
            if (Character.isLowerCase(ch)) isLowerCase = true; //
            if (symbols.indexOf(ch) >= 0) isSpecialSymbol = true;

        }

//       System.out.printf("%s | %s | %s | %s\n", isDigit, isUpperCase, isLowerCase, isSpecialSymbol);

        String errorMessage = "Errors! should be a: ";
        int initLength = errorMessage.length();


        // errorMessage += "Dtr"; // -> errorMessage = errorMessage + "Dtr;"
        //  int x = 10;
        // x -= 5; // x = x - 5;

        if (!isDigit) errorMessage += "digit in password; "; // "Errors! should be a: digit in password; "
        if (!isLowerCase) errorMessage += "lower case letter in password; "; // "Errors! should be a: digit in password; lower case letter in password;"
        if (!isUpperCase) errorMessage += "upper case letter in password; "; // "Errors! should be a: upper case letter in password; "
        if (!isSpecialSymbol) errorMessage += "special symbol in password; "; // "Errors! should be a: digit in password; lower case letter in password; special symbol in password;"

        // Несколько вариантов проверить, прошел ли пароль валидацию
//        if (errorMessage.length() > initLength) throw new PasswordValidateException(errorMessage);

        // Проверяем, если хотя бы в одной из переменных окажется false, то пароль не прошел валидацию. Бросаем исключение.
        if (!(isDigit && isUpperCase && isLowerCase && isSpecialSymbol)) throw new utils.PasswordValidateException(errorMessage);


        // Если хотя бы в одной переменной останется значение false, то весь пароль НЕ будет признан валидным = (признан не валидным)
//        return isDigit && isUpperCase && isLowerCase && isSpecialSymbol;

    }

}