package utils.validator;

/**
 * @author olgakharina
 * @date 20.11.24
 */

/**
 * Исключение для обработки ошибок валидации пароля.
 */
public class PasswordValidateException extends Exception {

    /**
     * Конструктор исключения с сообщением об ошибке.
     *
     * @param message Сообщение, описывающее причину исключения.
     */
    public PasswordValidateException(String message) {
        super(message);
    }

    /**
     * Переопределение метода getMessage для более подробного описания исключения.
     *
     * @return Сообщение об исключении.
     */
    @Override
    public String getMessage() {
        return "Password validation exception: " + super.getMessage();
    }
}
