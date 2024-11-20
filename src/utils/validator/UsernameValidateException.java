package utils.validator;

/**
 * @author olgakharina
 * @date 20.11.24
 *//**
 * Исключение для обработки ошибок валидации имени пользователя.
 */
public class UsernameValidateException extends Exception {

  /**
   * Конструктор исключения с сообщением об ошибке.
   *
   * @param message Сообщение, описывающее причину исключения.
   */
  public UsernameValidateException(String message) {
    super(message);
  }

  /**
   * Переопределение метода getMessage для более подробного описания исключения.
   *
   * @return Сообщение об исключении.
   */
  @Override
  public String getMessage() {
    return "Username validation exception: " + super.getMessage();
  }
}
